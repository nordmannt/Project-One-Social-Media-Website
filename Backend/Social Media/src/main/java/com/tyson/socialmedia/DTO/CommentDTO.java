package com.tyson.socialmedia.DTO;

public class CommentDTO {
    private Long commentId;
    private Long commentedBy;
    private Long messageId;
    private String commentText;
    private String timePosted;
    private String firstName;
    private String lastName;
    private Integer likeCount;
    private Integer userId;

    public Integer getLikeCount(){
        return likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }

    
    public Long getCommentId(){
        return commentId;
    }
    
    public void setCommentId(Long commentId){
        this.commentId = commentId;
    }

    public Long getCommentedby(){
        return commentedBy;
    }
    
    public void setCommentedBy(Long commentedBy){
        this.commentedBy = commentedBy;
    }

    public Long getMessageId(){
        return messageId;
    }
    
    public void setMessageId(Long messageId){
        this.messageId = messageId;
    }

    public String getCommentText(){
        return commentText;
    }
    
    public void setCommentText(String commentText){
        this.commentText = commentText;
    }

    public String getTimePosted(){
        return timePosted;
    }
    
    public void setTimePosted(String timePosted){
        this.timePosted = timePosted;
    }

    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    

}
