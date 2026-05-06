/**
 * Cluster.java
 * 
 * KIT107 Assignment 2 -- Cluster Implementation
 * 
 * @author Kiran Chhantyal(770872)
 * @version	6/5/2026
 */


public class Cluster implements ClusterInterface
{
    // final instance variables
// (none needed)

    
    // instance variables
protected Node firstPlayer;


	/**
	 * Constructor
	 * 
	 * Precondition: None
	 * Postcondition: The new instance will have its instance variable(s)
     *                  initialised.
	 * Informally: Initialise the cluster of players.
	 */
    public Cluster()
    {
        firstPlayer = null;
    }

	/**
	 * isEmpty()
	 * 
	 * @return boolean -- whether the cluster is empty
	 * 
	 * Precondition: None
	 * Postcondition: True is returned if the Cluster is empty; false is
     *                  returned otherwise.
	 * Informally: Check whether the Cluster is empty.
	 */
    public boolean isEmpty()
    {
	return firstPlayer == null; // to get past the compiler, use: return true;
    }

    /**
	 * addPlayerToCluster()
	 * 
	 * @param p Player -- the player to add to this cluster
	 * 
	 * Precondition: The given Player parameter has been constructed.
	 * Postcondition: The given Player has been added to the Cluster of
     *                  players preserving the alphabetical order and
     *                  secondarily ordering by games played.
	 * Informally: Add a player to the Cluster.
	 */
    public void addPlayerToCluster(Player p)
    {

		Node current = firstPlayer;
		Node previous = null;
		// Step 1: check if player already exists
		while (current != null)
		{
			Player existing =(Player) current.getData();
		if (existing.getName().equals(p.getName()))
			{
				existing.update(p);
				return;
			}
			previous = current;
			current = current.getNext();
		
		}
		Node newNode = new Node(p);
		current = firstPlayer;
		previous = null;
		while (current != null)
		{
			Player existing = (Player) current.getData();
			if (existing.getName().compareTo(p.getName()) > 0)
			{
				break;
			}
			previous = current;
			current = current.getNext();
			
		}
		if (previous == null)
		{
			newNode.setNext(firstPlayer);
			firstPlayer = newNode;
		}
		else 
		{
			newNode.setNext(current);
			previous.setNext(newNode);
		}
	}
 	/**
	 * getFirstPlayer()
	 * 
	 * @return Player -- the first player in the cluster
	 * 
	 * Precondition: None
	 * Postcondition: the first player in the cluster is returned if the
     *                  cluster is non-empty; null is returned otherwise.
	 * Informally: Get the first player in thge cluster.
	 */
    public Player getFirstPlayer()
    {
        if (firstPlayer == null)
		{
			return null;
		}
		return (Player) firstPlayer.getData();
    }

   /**
	 * countPlayers()
	 * 
	 * @return int -- the number of players in the cluster
	 * 
	 * Precondition: None
	 * Postcondition: The number of players in the Cluster has been counted and
     *                  returned.
	 * Informally: Produce a count of players within the current Cluster.
	 */
	// counts the number of players in the cluster
    public int countPlayers()
    {
        int count = 0;
        Node current = firstPlayer;
        while (current != null)
        {
            count++;
            current = current.getNext();
        }
        return count;
    }

    /**
	 * most()
	 * 
	 * @param x char -- the category to search ('g'oals, 'd'isposals, 'c'langers,
     *                      frees 'a'gainst, or ga'm'es)
     * @return Player -- the player with the highest value in the specified
     *                      category
     * 
     * Precondition: None.
	 * Postcondition: All players in the Cluster are searched for the given maximum
     *                  in the category indicated (x) and the player with the
     *                  highest is returned.  If there are multiple players then the
     *                  last found is returned; if there are no data then null is
     *                  returned.
	 * Informally: Find the player in the Cluster with the maximum value in the 
     *                  given category.
	 */
    public Player most(char x)
    {
		if (isEmpty()) // to get past the compiler, use: return null;
    {
		return null;
	}
	Node current = firstPlayer;
	Player best = (Player) current.getData();
	while (current != null)
	{
		Player p = (Player) current.getData();
		if (getStat(p, x) >= getStat(best, x))
		{
			best = p;
		}
	
		current = current.getNext();
	}
	return best;
}
    /**
	 * summary()
	 * 
	 * @return String -- the summary of statistics for the current Cluster (i.e. team)
	 * 
	 * Precondition: None
	 * Postcondition: A String has been returned which is the summary of the current
     *                  team's statistics, or "" if the cluster is empty.
	 * Informally: Produce a summary of the current Cluster.
	 */
    public String summary()
    {
 // to get past the compiler, use: return "";
 if (isEmpty())
    {
        return "";
    }
Player total = new Player("Total", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0);
Node current = firstPlayer;
while (current != null)
{

	total.update((Player) current.getData());
	current = current.getNext();
}
return total.toString();
	}
	/**
	 * toString()
	 
	 * @return String -- printable form of the Cluster of players
	 * 
	 * Precondition: None
	 * Postcondition: A printable (String) form of the players data is
     *                  returned, one player per line.  If there are no
     *                  players then "" is returned.
	 * Informally: Convert the Cluster of players data to a multi-line
     *                  String.
	 */
    public String toString()
    {
 // to get past the compiler, use: return "";
 String result = "";
 Node current = firstPlayer;
 while (current != null)
 {
     result += current.getData().toString();
     current = current.getNext();
 }
 return result;
}
// helper method to generate histogram stats
private int getStat(Player p, char x)
{
	switch (x)
	{
		case 'a': return p.getFreesAgainst();
		case 'c': return p.getClangers();
		case 'd': return p.getDisposals();
		case 'g': return p.getGoals();
		case 'm': return p.getGames();
		default: return 0;
	}
}

}