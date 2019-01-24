package ic.doc;

import java.lang.reflect.Array;
import java.util.*;

public class QueryProcessor {
  /*Add a map mapping query to answer; change if to switch?;*/

  private final Map<List<String>, String> queryMap = new HashMap<>();
  private final List<Query> queriesMap = new ArrayList<>();

  public QueryProcessor() {
    /*this.queryMap*/
    this.queriesMap.add(new Query(Arrays.asList("william", "shakespeare"),
        "William Shakespeare (26 April 1564 - 23 April 1616) was an\n" +
            "English poet, playwright, and actor, widely regarded as the greatest\n" +
            "writer in the English language and the world's pre-eminent dramatist. \n",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/702px-Shakespeare.jpg",
        "https://en.wikipedia.org/wiki/William_Shakespeare"));

    this.queriesMap.add(new Query(Arrays.asList("isaac", "asimov"),
        "Isaac Asimov (2 January 1920 - 6 April 1992) was an\n" +
            "American writer and professor of Biochemistry, famous for\n" +
            "his works of hard science fiction and popular science. \n",
        "",
        ""));

    this.queriesMap.add(new Query(Arrays.asList("alan", "mathison", "turing"),
        "Alan Mathison Turing (23 June 1912 - 7 June 1954) was an English\n" +
            "mathematician, computer scientist, logician, cryptanalyst, philosopher\n" +
            "and theoretical biologist. Turing was highly influential in the\n" +
            "development of theoretical computer science, providing a formalisation\n" +
            "of the concepts of algorithm and computation with the Turing machine,\n" +
            "which can be considered a model of a general-purpose computer. Turing\n" +
            "is widely considered to be the father of theoretical computer science\n" +
            "and artificial intelligence. Despite these accomplishments, he was\n" +
            "never fully recognized in his home country during his lifetime due to\n" +
            "his homosexuality, which was then a crime in the UK.",
        "",
        ""));

    this.queriesMap.add(new Query(Arrays.asList("augusta", "ada", "king", "lovelace"),
        "Augusta Ada King, Countess of Lovelace (10 December 1815 - 27 November\n" +
            "November 1852) was an English mathematician and writer, chiefly\n" +
            "known for her work on Charles Babbage's proposed mechanical\n" +
            "general-purpose computer, the Analytical Engine. She was the first to\n" +
            "recognise that the machine had applications beyond pure calculation,\n" +
            "and published the first algorithm intended to be carried out by such\n" +
            "a machine. As a result, she is sometimes regarded as the first to\n" +
            "recognise the full potential of a \"computing machine\" and the first\n" +
            "computer programmer.",
        "",
        ""));

    this.queriesMap.add(new Query(Arrays.asList("william", "pascal"),
        "Blaise Pascal (19 June 1623 - 19 August 1662) was a French\n" +
            "mathematician, physicist, inventor, writer and Catholic theologian.\n" +
            "He was a child prodigy who was educated by his father, a tax collector\n" +
            "in Rouen. Pascal's earliest work was in the natural and applied\n" +
            "sciences where he made important contributions to the study of fluids,\n" +
            "and clarified the concepts of pressure and vacuum by generalising the\n" +
            "work of Evangelista Torricelli. Pascal also wrote in defence of the\n" +
            "scientific method.",
        "",
        ""));
    /*this.queryMap.put(Arrays.asList("william", "shakespeare"),
            "William Shakespeare (26 April 1564 - 23 April 1616) was an\n" +
                    "English poet, playwright, and actor, widely regarded as the greatest\n" +
                    "writer in the English language and the world's pre-eminent dramatist. \n");
*/
    /*this.queryMap.put(Arrays.asList("isaac", "asimov"),
            "Isaac Asimov (2 January 1920 - 6 April 1992) was an\n" +
                    "American writer and professor of Biochemistry, famous for\n" +
                    "his works of hard science fiction and popular science. \n");*/

    /*this.queryMap.put(Arrays.asList("alan", "mathison", "turing"),
            "Alan Mathison Turing (23 June 1912 - 7 June 1954) was an English\n" +
                    "mathematician, computer scientist, logician, cryptanalyst, philosopher\n" +
                    "and theoretical biologist. Turing was highly influential in the\n" +
                    "development of theoretical computer science, providing a formalisation\n" +
                    "of the concepts of algorithm and computation with the Turing machine,\n" +
                    "which can be considered a model of a general-purpose computer. Turing\n" +
                    "is widely considered to be the father of theoretical computer science\n" +
                    "and artificial intelligence. Despite these accomplishments, he was\n" +
                    "never fully recognized in his home country during his lifetime due to\n" +
                    "his homosexuality, which was then a crime in the UK.");*/

/*    this.queryMap.put(Arrays.asList("augusta", "ada", "king", "lovelace"),
            "Augusta Ada King, Countess of Lovelace (10 December 1815 - 27 November\n" +
                    "November 1852) was an English mathematician and writer, chiefly\n" +
                    "known for her work on Charles Babbage's proposed mechanical\n" +
                    "general-purpose computer, the Analytical Engine. She was the first to\n" +
                    "recognise that the machine had applications beyond pure calculation,\n" +
                    "and published the first algorithm intended to be carried out by such\n" +
                    "a machine. As a result, she is sometimes regarded as the first to\n" +
                    "recognise the full potential of a \"computing machine\" and the first\n" +
                    "computer programmer.");*/

/*    this.queryMap.put(Arrays.asList("william", "pascal"),
            "Blaise Pascal (19 June 1623 - 19 August 1662) was a French\n" +
                    "mathematician, physicist, inventor, writer and Catholic theologian.\n" +
                    "He was a child prodigy who was educated by his father, a tax collector\n" +
                    "in Rouen. Pascal's earliest work was in the natural and applied\n" +
                    "sciences where he made important contributions to the study of fluids,\n" +
                    "and clarified the concepts of pressure and vacuum by generalising the\n" +
                    "work of Evangelista Torricelli. Pascal also wrote in defence of the\n" +
                    "scientific method.");*/

  }

  public String process(String query) {
    return search(query);
  }

  private String search(String query) {
    /*For same surnames, add to a list instead of returning, if list length
    is more than 1, then generate choice page, else return answer*/

    for(Query q:queriesMap) {
      List<String> name = q.getQuery();
      boolean found = true;
      StringTokenizer queryTokens = new StringTokenizer(query);
      for(int i = 0; queryTokens.hasMoreTokens(); i++){
        found &= name.contains(queryTokens.nextToken().toLowerCase());
      }
      if (found) {
        return q.getAnswer().concat("\n");
      }
    }

    return "";
  }
}
