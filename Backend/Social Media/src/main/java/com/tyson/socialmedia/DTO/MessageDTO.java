package com.tyson.socialmedia.DTO;

public class MessageDTO {
 private Long messageId;
 private String messageText;
 private Long timePosted;
 private String firstName;
 private String lastName;
 private Long postedBy;
 private Integer likeCount;
 

public Integer getLikeCount(){
    return likeCount;
}

public void setLikeCount(Integer likeCount){
    this.likeCount = likeCount;
}


 public void setMessageId(Long messageId){
    this.messageId = messageId;
 }

 public void setMessageText(String messageText){
    this.messageText = messageText;
 }

 public void setPostedBy(Long postedBy){
    this.postedBy = postedBy;
 }

 public void setTimePosted(Long timePosted){
    this.timePosted = timePosted;
 }

 public void setFirstName(String firstName){
    this.firstName = firstName;
 }

public void setLastName(String lastName){
    this.lastName = lastName;
}

public Long getMessageId(){
    return messageId;
}

public String getMessageText(){
    return messageText;
}

public Long getTimePosted(){
    return timePosted;
}

public String getFirstName(){
    return firstName;
}

public String getLastName(){
    return lastName;
}

public Long getPostedBy(){
    return postedBy;
}
    
}
