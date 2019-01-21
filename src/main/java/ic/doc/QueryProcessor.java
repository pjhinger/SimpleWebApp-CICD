package ic.doc;

public class QueryProcessor {

  public String process(String query) {
    StringBuilder results = new StringBuilder();
    if (query.toLowerCase().contains("shakespeare")) {
      results.append("William Shakespeare (26 April 1564 - 23 April 1616) was an\n" +
                     "English poet, playwright, and actor, widely regarded as the greatest\n" +
                     "writer in the English language and the world's pre-eminent dramatist. \n");
      results.append(System.lineSeparator());
    }

    if (query.toLowerCase().contains("asimov")) {
      results.append("Isaac Asimov (2 January 1920 - 6 April 1992) was an\n" +
                     "American writer and professor of Biochemistry, famous for\n" +
                     "his works of hard science fiction and popular science. \n");
      results.append(System.lineSeparator());
    }

    if (query.toLowerCase().contains("turing")) {
      results.append("Alan Mathison Turing (23 June 1912 - 7 June 1954) was an English\n" +
                     "mathematician, computer scientist, logician, cryptanalyst, philosopher\n" +
                     "and theoretical biologist. Turing was highly influential in the\n" +
                     "development of theoretical computer science, providing a formalisation\n" +
                     "of the concepts of algorithm and computation with the Turing machine,\n" +
                     "which can be considered a model of a general-purpose computer. Turing\n" +
                     "is widely considered to be the father of theoretical computer science\n" +
                     "and artificial intelligence. Despite these accomplishments, he was\n" +
                     "never fully recognized in his home country during his lifetime due to\n" +
                     "his homosexuality, which was then a crime in the UK.");
      results.append(System.lineSeparator());
    }

    if (query.toLowerCase().contains("lovelace")) {
      results.append("Augusta Ada King, Countess of Lovelace (10 December 1815 - 27 November\n" +
                     "November 1852) was an English mathematician and writer, chiefly\n" +
                     "known for her work on Charles Babbage's proposed mechanical\n" +
                     "general-purpose computer, the Analytical Engine. She was the first to\n" +
                     "recognise that the machine had applications beyond pure calculation,\n" +
                     "and published the first algorithm intended to be carried out by such\n" +
                     "a machine. As a result, she is sometimes regarded as the first to\n" +
                     "recognise the full potential of a \"computing machine\" and the first\n" +
                     "computer programmer.");
      results.append(System.lineSeparator());
    }

    if (query.toLowerCase().contains("pascal")) {
      results.append("Blaise Pascal (19 June 1623 - 19 August 1662) was a French\n" +
                     "mathematician, physicist, inventor, writer and Catholic theologian.\n" +
                     "He was a child prodigy who was educated by his father, a tax collector\n" +
                     "in Rouen. Pascal's earliest work was in the natural and applied\n" +
                     "sciences where he made important contributions to the study of fluids,\n" +
                     "and clarified the concepts of pressure and vacuum by generalising the\n" +
                     "work of Evangelista Torricelli. Pascal also wrote in defence of the\n" +
                     "scientific method.");
      results.append(System.lineSeparator());
    }

    return results.toString();
  }
}
