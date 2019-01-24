package ic.doc;

import java.util.List;

public class Query {
  private List<String> query;
  private String answer;
  private String imgURL;
  private String wikiURL;

  public Query(List<String> query, String answer, String imgURL, String wikiURL) {
    this.query = query;
    this.answer = answer;
    if(query != null) {
      this.answer = this.answer.concat("\n");
    }
    this.imgURL = imgURL;
    this.wikiURL = wikiURL;
  }

  public List<String> getQuery() {
    return query;
  }

  public String getAnswer() {
    return answer;
  }

  public String getImgURL() {
    return imgURL;
  }

  public String getWikiURL() {
    return wikiURL;
  }

}
