/**
 * Collection.java
 * 
 * KIT107 Assignment 2 -- Collection Implementation
 * 
 * @author Kiran Chhantyal(770872)
 * @version	6/5/2026
 */


public class Collection implements CollectionInterface
{
    // final instance variables
// (none needed)

    
    // instance variables
protected Node firstTeam;

    
	/**
	 * Constructor
	 * 
	 * Precondition: None
	 * Postcondition: The new instance will have its instance variable(s)
     *                  initialised.
	 * Informally: Initialise the Collection of player clusters ('teams').
	 */
    public Collection()
    {
firstTeam = null;
    }

	/**
	 * isEmpty()
	 * 
	 * @return boolean -- whether the collection is empty
	 * 
	 * Precondition: None
	 * Postcondition: True is returned if the Collection is empty; false is
     *                  returned otherwise.
	 * Informally: Check whether the Collection is empty.
	 */
    public boolean isEmpty()
    {
 // to get past the compiler, use: return true;
 return firstTeam == null;
    }

    /**
	 * addPlayerToCollection()
	 * 
	 * @param p Player -- the player to add to this collection
	 * 
	 * Precondition: The given Player parameter has been constructed
	 * Postcondition: The given Player has been added to the Collection and,
     *                  in particular to the appropriate cluster of players
     *                  based on the team name.
	 * Informally: Add a player to the appropriate 'team' in the
     *                  Collection.
	 */
    public void addPlayerToCollection(Player p)
    {
        Node current = firstTeam;
        Node previous = null;

        // check if team already exists
        while (current != null) {
            Cluster c = (Cluster) current.getData();
            Player first = c.getFirstPlayer();
            if(first != null && first.getTeam().equals(p.getTeam())) {
                c.addPlayerToCluster(p);
                return;
            }
            previous = current;
            current = current.getNext();
        }
        // create new cluster
        Cluster newCluster = new Cluster();
        newCluster.addPlayerToCluster(p);

        Node newNode = new Node(newCluster);
        // insert alphabetically
        current = firstTeam;
        previous = null;
        while (current != null)
        {
            Cluster c = (Cluster) current.getData();
            Player first = c.getFirstPlayer();
            if (first != null && first.getTeam().compareTo(p.getTeam()) > 0)
            {
            break;

        }
        previous = current;
        current = current.getNext();
    }
        if (previous == null) {
            newNode.setNext(firstTeam);
            firstTeam = newNode;
        } else {
            previous.setNext(newNode);
            newNode.setNext(current);
        }
    }

    /**
	 * showPlayerHistogram()
	 * 
	 * Precondition: None
	 * Postcondition: The Collection is traversed cluster by cluster.  A
     *                  row comprising cluster name, a star for each
     *                  player in the cluster, and the total number of
     *                  players in the cluster is printed.  The message
     *                  "No data!" should be printed if the Collection is 
     *                  empty.
	 * Informally: Print the horizontal histogram of players per team
	 */
    public void showPlayerHistogram()
    {
     if (isEmpty()) {
        System.out.println("No data!");
        return;
        }

        Node current = firstTeam;

        while (current != null)
             {
            Cluster c = (Cluster) current.getData();
            Player first = c.getFirstPlayer();
            if (first != null) {
                String teamName = first.getTeam();
                int count = c.countPlayers();

                // print team name
                System.out.print(" " + teamName + " | ");

                // print stars
                for(int i = 0; i < count; i++) {
                    System.out.print("*");
                }
                // print count
                System.out.println(" " + count );
            }
            current = current.getNext();
    }}

    /**
	 * most()
	 * 
	 * @param x char -- the category to search: frees-(a)gainst, (c)langers, 
     *                      (d)isposals, (g)oals, or ga(m)es
     *
     * @return String -- the printable form of the identified output
     *  
     * Precondition: None.
	 * Postcondition: All players in all teams in the Collection are searched to
     *                  find the player with the largest value in the given 
     *                  category (x).  The message "No data!" is returned if the 
     *                  Collection is empty.  In the case of a tie, the player
     *                  found last is the one whose data is returned.
	 * Informally: Search every player in the cluster for a value in the given
     *                  category and print the name and URL for all that match.
     */
    public String most(char x)
    {
 // to get past the compiler, use: return "";
 if (isEmpty()) {
        return "No data!";
    }
    Node current = firstTeam;
    Player best = null;
    while (current != null) {
        Cluster c = (Cluster) current.getData();
        Player p = c.most(x);
        if (p != null) {
            if (best == null || getStat(p, x) >= getStat(best, x)) {
                best = p;
            }
        }
        current = current.getNext();
    }
    return best.toString();
    }

    /**
	 * summarise()
	 * 
     * @param t String -- the name of the desired team
     * 
	 * Precondition: None
	 * Postcondition: The Collection is traversed cluster by cluster.  The
     *                  aggregated statistics of all players for the chosen
     *                  team is calculated and printed.  The message "No data!" 
     *                  should be printed if the Collection is empty; the
     *                  message "Team (t) not found!" should be printed if the
     *                  Collection is not empty but there is no team with the
     *                  given team name present.
	 * Informally: Process the entire Collection displaying the combined
     *                  statistics for the given team.
	 */
    public void summarise(String t)
    {
        // check empty
    if (isEmpty()) {
            System.out.println("No data!");
            return;

    }
    Node current = firstTeam;
    // traverse teams
    while (current != null) {
        Cluster c = (Cluster) current.getData();
        Player first = c.getFirstPlayer();
        if (first != null && first.getTeam().equals(t)) {
          // Print summary if team is found
            System.out.println(c.summary());
            return;
        }
        current = current.getNext();
    }
    // team not found
    System.out.println("Team " + t + " not found!");
}

	/**
	 * toString()
	 * 
	 * @return String -- printable form of the Collection of players
	 * 
	 * Precondition: None
	 * Postcondition: A printable (String) form of the Collections's 
     *                  players data is returned, one player per line.  If
     *                  there are no players then "" is returned.
	 * Informally: Convert the Collection of players data to a multi-line
     *                  String.
	 */
    public String toString()
    {
 // to get past the compiler, use: return "";
 String result = "";
    Node current = firstTeam;
    while (current != null) 
        {
        Cluster c = (Cluster) current.getData();
        result += c.toString();
        current = current.getNext();
    }
    return result;
}

private int getStat(Player p, char x) {
    switch (x) {
        case 'a':
            return p.getFreesAgainst();
        case 'c':
            return p.getClangers();
        case 'd':
            return p.getDisposals();
        case 'g':
            return p.getGoals();
        case 'm':
            return p.getGames();
        default:
            return 0;
    }
}}