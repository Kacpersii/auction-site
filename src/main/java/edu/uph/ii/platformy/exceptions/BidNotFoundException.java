package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such auction")
public class BidNotFoundException extends RuntimeException{

    public BidNotFoundException(){
        super(String.format("Oferta nie istnieje"));
    }

    public BidNotFoundException(Long id){
        super(String.format("Oferta o id %d nie istnieje", id));
    }
}
