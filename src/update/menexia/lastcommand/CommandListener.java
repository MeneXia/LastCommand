package update.menexia.lastcommand;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CommandListener implements Listener
{
	public static LastCommand plugin;
	
	public CommandListener( LastCommand instance )
	{
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerQuit( PlayerQuitEvent e )
	{
		LastCommand.removePlayer( e.getPlayer() );
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess( PlayerCommandPreprocessEvent e ) 
	{
		if( !e.getMessage().equalsIgnoreCase("/l") && !e.getMessage().equalsIgnoreCase("/last") 
		&& !e.getMessage().equals("/!") && !e.getMessage().equalsIgnoreCase("/.") 
		&& !e.getMessage().equalsIgnoreCase("//") )
		{
			LastCommand.setLastCommand( e.getPlayer(), e.getMessage() );
		}
	}

}
