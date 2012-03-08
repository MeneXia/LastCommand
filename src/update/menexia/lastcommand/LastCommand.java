package update.menexia.lastcommand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class LastCommand extends JavaPlugin 
{
	public static List<String> playerList = new ArrayList<String>();
	public static List<String> commandList = new ArrayList<String>();
	public static String appName = null;
	public static boolean isEnabled = false;
	public static long lastLogChange = 0;
	public static CommandHandler cmdHandler; 

	public void onDisable() 
	{
		isEnabled = false;
		PluginDescriptionFile pdfFile = this.getDescription();
		logOutput(" version " + pdfFile.getVersion() + " by MeneXia is now disabled!");
	}

	public void onEnable() 
	{
		this.getServer().getPluginManager().registerEvents(new CommandListener(this), this);

		PluginDescriptionFile pdfFile = this.getDescription();
		appName = pdfFile.getName();
		isEnabled = true;

		cmdHandler = new CommandHandler(this);
		
		getCommand("last").setExecutor(cmdHandler);

		logOutput(" version " + pdfFile.getVersion() + " by MeneXia is now enabled!");
	}
	
	public static String getLastCommand( Player player )
	{
		String retVal = null;
		String who = null;
		
		for( int x = 0; x < playerList.size(); x++ )
		{
			who = playerList.get(x).toString();
			
			if( who == player.getName() )
			{
				retVal = commandList.get(x);
				break;
			}
		}

		return retVal;
	}
	
	public static void setLastCommand( Player player, String pCommand )
	{
		String who = null;
		boolean found = false;
		
		for( int x = 0; x < playerList.size(); x++ )
		{
			who = playerList.get(x).toString();
			
			if( who == player.getName() )
			{
				found = true;
				commandList.set(x, pCommand );
				break;
			}
		}
		
		if( !found )
		{
			playerList.add(player.getName());
			commandList.add( pCommand );
		}
	}

	public static void removePlayer( Player player )
	{
		int found = -1;
		String who = null;
		
		for( int x = 0; x < playerList.size(); x++ )
		{
			who = playerList.get(x).toString();
			
			if( who == player.getName() )
			{
				found = x;
				break;
			}
		}
		
		if( found != -1 )
		{
			commandList.remove( found );
			playerList.remove( found );
		}
	}

	public static void logOutput(String output)
	{
		System.out.println( "[LastCommand] " + output );
	}

	public static String arrayToString(String[] a, String separator) 
    {
        String result = "";
        
        if (a.length > 0) 
        {
            result = a[0];    // start with the first element
            for (int i=1; i<a.length; i++) {
                result = result + separator + a[i];
            }
        }
        return result;
    }
}
