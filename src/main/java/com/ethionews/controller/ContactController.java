package com.ethionews.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ethionews.model.Contact;
import com.ethionews.service.ContactService;
import com.ethionews.util.EnConstants;

@Controller
public class ContactController {
	private static final Logger logger = Logger.getLogger(ContactController.class);

	/*
	 * @Autowired
	 * 
	 * @Qualifier("contactValidator") private Validator validator;
	 * 
	 * @InitBinder private void initBinder(WebDataBinder binder) {
	 * binder.setValidator(validator); }
	 */

	@Autowired
	private ContactService contactService;

	@RequestMapping("createContact")
	public ModelAndView createContact(@ModelAttribute Contact contact) {
		logger.info("Creating Contact. Data: " + contact);
		return new ModelAndView("contactForm");
	}

	@RequestMapping("editContact")
	public ModelAndView editContact(@RequestParam long id, @ModelAttribute Contact contact) {
		logger.info("Updating the Contact for the Id " + id);
		contact = contactService.getContact(id);
		return new ModelAndView("contactForm", "contactObject", contact);
	}

	@RequestMapping("saveContact")
	public String saveContact(Model model, @Validated Contact contact, BindingResult result) {
		logger.info("Saving the Contact. Data : " + contact);
		// if contact id is 0 then creating the contact other updating the
		// contact
		String returnVal = "redirect:createContact";
		if (result.hasErrors()) {
			return "contactForm";
		} else {
			if (contact.getId() == 0) {
				contactService.createContact(contact);
			} else {
				contactService.updateContact(contact);
			}

		}

		return returnVal;
	}

	@RequestMapping("deleteContact")
	public ModelAndView deleteContact(@RequestParam long id) {
		logger.info("Deleting the Contact. Id : " + id);
		contactService.deleteContact(id);
		return new ModelAndView("redirect:getAllContacts");
	}

	@RequestMapping("getAllContacts")
	public ModelAndView getAllContacts() {
		logger.info("Getting the all Contacts.");
		List<Contact> contactList = contactService.getAllContacts();
		return new ModelAndView("contactList", "contactList", contactList);
	}

	@RequestMapping("searchContact")
	public ModelAndView searchContact(@RequestParam("roleType") String roleType) {
		logger.info("Searching the Contact. Contact Names: " + roleType);
		List<Contact> contactList = contactService.getAllContacts(roleType);
		return new ModelAndView(EnConstants.MEDIA_LIST, EnConstants.MEDIA_LIST, contactList);
	}

}