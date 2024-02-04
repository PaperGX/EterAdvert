package fr.etercube.eteradvert.event;

import fr.etercube.eteradvert.EterAdvert;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerCloseInventory implements Listener {
    private EterAdvert eterAdvert;
    public PlayerCloseInventory(EterAdvert eterAdvert) {
        this.eterAdvert = eterAdvert;
    }

    @EventHandler
    public void PlayerCloseInventory(InventoryCloseEvent event) {
        if(!eterAdvert.getListPlayerInAdvert().contains(event.getPlayer())) return;

        Player player = (Player) event.getPlayer();

        HashMap<String, String> permissionList = eterAdvert.getPermissionList();
        HashMap<String, String> messageList = eterAdvert.getMessageList();

        for(Player p : Bukkit.getOnlinePlayers()){

            if(p.hasPermission(permissionList.get("seeinventoryclose"))){
                p.sendMessage(messageList.get("closeinventory").replace("%player%", player.getName()));
            }

        }

        ArrayList<Player> listPlayerInAdvert = eterAdvert.getListPlayerInAdvert();
        listPlayerInAdvert.remove(player);
        eterAdvert.setListPlayerInAdvert(listPlayerInAdvert);

    }
}
