package com.api.codeplatform.controllers;

import com.api.codeplatform.services.PieceOfCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/code")
public class WebController {

    private final PieceOfCodeService pieceOfCodeService;

    @Autowired
    public WebController(PieceOfCodeService pieceOfCodeService) {
        this.pieceOfCodeService = pieceOfCodeService;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String getCodeById(Model model, @PathVariable UUID id){
        return pieceOfCodeService.getCodeHTMLById(model, id);
    }

    @RequestMapping(value="/new", method= RequestMethod.GET)
    public String getNewCode(Model model){
        return pieceOfCodeService.newCodeHTML(model);
    }

    @RequestMapping(value="/latest", method= RequestMethod.GET)
    public String getLatestCodes(Model model){
        return pieceOfCodeService.latestCodesHTML(model);
    }
}
