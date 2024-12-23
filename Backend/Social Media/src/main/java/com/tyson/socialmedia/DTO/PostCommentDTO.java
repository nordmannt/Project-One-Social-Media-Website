package com.tyson.socialmedia.DTO;



public class PostCommentDTO {
    private String commentText;
    private Integer commentedBy;
    private Integer messageId;
    private Integer likeCount;

    public Integer getLikeCount(){
        return likeCount;
    }
    
    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }

    public String getCommentText(){
        return commentText;
    }
    public void setCommentText(String commentText){
        this.commentText = commentText;
    }
    public Integer getCommentedBy(){
        return commentedBy;
    }
    public void setCommentedBy(Integer commentedBy){
        this.commentedBy = commentedBy;
    }
    public Integer getMessageId(){
        return messageId;
    }
    public void setMessageId(Integer messageId){
        this.messageId = messageId;
    }
    
}
