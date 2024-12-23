package com.tyson.socialmedia.DTO;



public class PostDTO {
    private String messageText;
    private Long postedBy;
    private Integer likeCount;

    public Integer getLikeCount(){
        return likeCount;
    }
    
    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }

    public String getMessageText(){
        return messageText;
    }
    public void setMessageText(String messageText){
        this.messageText = messageText;
    }
    public Long getPostedBy(){
        return postedBy;
    }
    public void setPostedBy(Long postedBy){
        this.postedBy = postedBy;
    }



}
