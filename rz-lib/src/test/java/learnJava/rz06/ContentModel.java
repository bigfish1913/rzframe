package learnJava.rz06;

public class ContentModel {
 private ContentHeader contentHeader;
 private String content;
 
 public ContentModel(String content) {
  this.content = content;
  contentHeader = new ContentHeader();
  contentHeader.setMsgLength(content.getBytes().length);
 }
 
 public String getContent() {
  return content;
 }
 
 public ContentHeader getContentHeader() {
  return contentHeader;
 }
}
