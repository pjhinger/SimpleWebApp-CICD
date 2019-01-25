package ic.doc;

import java.util.List;

public class Query {
  private String query;
  private String description;
  private String imgURL;
  private String wikiURL;

  public Query(String query, String description, String imgURL, String wikiURL) {
    this.query = query;
    this.description = description;
    if(query != null) {
      this.description = this.description.concat("\n");
    }
    this.imgURL = imgURL;
    this.wikiURL = wikiURL;
  }

  public String getQuery() {
    return query;
  }

  public String getDescription() {
    return description;
  }

  public String getImgURL() {
    return imgURL;
  }

  public String getWikiURL() {
    return wikiURL;
  }

}
