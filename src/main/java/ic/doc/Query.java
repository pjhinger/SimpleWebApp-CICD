package ic.doc;

public class Query {
  private String name;
  private String description;
  private String imgURL;
  private String wikiURL;

  Query(String name, String description, String imgURL, String wikiURL) {
    this.name = name;
    this.description = description;
    if(name != null) {
      this.description = this.description.concat("\n");
    }
    this.imgURL = imgURL;
    this.wikiURL = wikiURL;
  }

  public String getName() {
    return name;
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
