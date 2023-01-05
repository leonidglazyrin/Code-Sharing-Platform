package com.api.codeplatform.services;

import com.api.codeplatform.exceptions.ApiException;
import com.api.codeplatform.model.PieceOfCode;
import com.api.codeplatform.repository.PieceOfCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class PieceOfCodeService {

    private final PieceOfCodeRepository pieceOfCodeRepository;

    @Autowired
    public PieceOfCodeService(PieceOfCodeRepository pieceOfCodeRepository) {
        this.pieceOfCodeRepository = pieceOfCodeRepository;
    }

    public PieceOfCode getCodeById(UUID id) {
        try{
            PieceOfCode snippet = this.pieceOfCodeRepository.findById(id).orElseThrow();
            checkTimedOut(snippet);
            adjustViewLimit(snippet);
            return snippet;
        } catch (Exception e) {
            throw new ApiException("Code snippet not found. I has expired or the UUID is isn't associated with any.");
        }
    }

    void adjustViewLimit(PieceOfCode snippet) {
        if(snippet.getViewsToDelete() == 1){
            snippet.setViewsToDelete(snippet.getViewsToDelete() - 1);
            this.pieceOfCodeRepository.deleteById(snippet.getId());
        } else if(snippet.getViewsToDelete() > 0){
            snippet.setViewsToDelete(snippet.getViewsToDelete() - 1);
            this.pieceOfCodeRepository.save(snippet);
        }
    }

    private void checkTimedOut(PieceOfCode snippet) {
        if(snippet.getSecondsToDelete() < 0){
            this.pieceOfCodeRepository.deleteById(snippet.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<PieceOfCode> getLatest() {
        return pieceOfCodeRepository.findTop10ByViewsAndTimeOrderByLoadDate(PageRequest.of(0,10));
    }

    public UUID addCode(PieceOfCode pieceOfCode) {
        pieceOfCode.setLoadDate();
        pieceOfCode.setId();
        PieceOfCode saved = this.pieceOfCodeRepository.save(pieceOfCode);
        return saved.getId();
    }

    public String getCodeHTMLById(Model model, UUID id) {
        PieceOfCode pieceOfCode = this.getCodeById(id);
        model.addAttribute("title", "Code");
        model.addAttribute("codeBody", pieceOfCode.getPieceOfCode());
        model.addAttribute("loadDate", pieceOfCode.getLoadDate());
        //This exists because I want to still show a message if 0 more views remain
        boolean restrictedViews =
                this.pieceOfCodeRepository.findById(id).isEmpty() || pieceOfCode.getViewsToDelete() > 0;
        model.addAttribute("restrictedViews", restrictedViews);
        //Normally if 0 views remain it means it doesn't have view limitations and thus
        // no message is show at all
        model.addAttribute("views", pieceOfCode.getViewsToDelete());
        model.addAttribute("time", pieceOfCode.getSecondsToDelete());
        return "code";
    }

    public String newCodeHTML(Model model) {
        model.addAttribute("title", "Create");
        model.addAttribute("isRenderPaste", true);
        return "code";
    }

    public String latestCodesHTML(Model model) {
        model.addAttribute("title", "Latest");
        model.addAttribute("latestCodes", this.getLatest());
        return "code";
    }
}
