package com.escandell.bank2.controller;

import com.escandell.bank2.persistence.entity.Account;
import com.escandell.bank2.persistence.entity.Coustumer;
import com.escandell.bank2.service.AccountService;
import com.escandell.bank2.service.CoustumerService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {
     private final AccountService accountService;
     private final CoustumerService coustumerService;

    public AccountController(AccountService accountService, CoustumerService coustumerService) {
        this.accountService = accountService;
        this.coustumerService = coustumerService;
    }


    @GetMapping(path = "/account")
    public ModelAndView Account(){
        return new ModelAndView("account/accountform")
                .addObject("account",new Account())
                .addObject("coustumerlis", coustumerService.ListAllCoustumer());
    }
    @PostMapping(path = "/account")
    public String AddAccount(@Validated Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("account", account);
            model.addAttribute("coustumerlis", coustumerService.ListAllCoustumer());
            return "account/accountform";
        }
        accountService.CreatedAccount(account);
        redirectAttributes.addFlashAttribute( "msgSucces", "The Account has been created successfully");
        return "redirect:/accountlist";
    }
    @GetMapping(path = "/accountlist")
    public ModelAndView AccountList(){
        return new ModelAndView("account/accountlist")
                .addObject("accountlist", accountService.ListAllAccount());
    }
    @GetMapping(path = "/{id}/editaccount")
    public ModelAndView GetAccountById(@PathVariable Long id){
        Account account= accountService.GetAccountById(id);
        Coustumer coustumer = coustumerService.GetCoustumerById(account.getCoustumer().getId());
        return new ModelAndView("account/accountform").addObject("account", account).addObject("coustumerlis",coustumer);
    }

    @PostMapping(path = "/{id}/editaccount")
    public String EditCoustumerById(@PathVariable Long id,@Validated Account account,BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        Account accountDB = accountService.GetAccountById(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("account", account);
            model.addAttribute("coustumerlis", coustumerService.ListAllCoustumer());
            return "account/accountform";
        }
        accountDB.setBalance(account.getBalance());


        accountService.CreatedAccount(accountDB);
        redirectAttributes.addFlashAttribute( "msgSucces", "The Account has been update successfully");
        return "redirect:/accountlist ";
    }

    @GetMapping(path = "/{id}/deletaccount")
    public ModelAndView DeleteAccount (@PathVariable(name = "id") Long id, ModelAndView view){
        accountService.DeleteAccountById(id);
        view.setViewName("account/accountlist");
        view.addObject("accountlist", accountService.ListAllAccount());
        return view;

    }




}
