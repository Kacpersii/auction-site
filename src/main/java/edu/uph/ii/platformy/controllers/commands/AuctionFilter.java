package edu.uph.ii.platformy.controllers.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter
public class AuctionFilter {

    private String title;

    private String description;

    private String category;

    public boolean isEmpty(){
        return StringUtils.isEmpty(title) && StringUtils.isEmpty(description) && StringUtils.isEmpty(category);
    }

    public void clear(){
        this.title = "";
        this.description = "";
        this.category = "";
    }

    public String getTitleLIKE(){
        if(StringUtils.isEmpty(title)) {
            return null;
        }else{
            return "%"+title+"%";
        }
    }

    public String getDescriptionLIKE(){
        if(StringUtils.isEmpty(description)) {
            return null;
        }else{
            return "%"+ description +"%";
        }
    }

    public String getCategoryLIKE(){
        if(StringUtils.isEmpty(category)) {
            return null;
        }else{
            return "%"+ category +"%";
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

}
