package edu.uph.ii.platformy.exceptions;

        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such auction")
public class AuctionNotFoundException extends RuntimeException{

    public AuctionNotFoundException(){
        super(String.format("Aukcja nie istnieje"));
    }

    public AuctionNotFoundException(Long id){
        super(String.format("Aukcja o id %d nie istnieje", id));
    }
}
