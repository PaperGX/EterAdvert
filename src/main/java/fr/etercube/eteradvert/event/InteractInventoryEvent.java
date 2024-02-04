package fr.etercube.eteradvert.event;

import fr.etercube.eteradvert.EterAdvert;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractInventoryEvent implements Listener {
    private EterAdvert eterAdvert;
    public InteractInventoryEvent(EterAdvert eterAdvert) {
        this.eterAdvert = eterAdvert;
    }

    @EventHandler
    public void inventoryInteractEvent(InventoryClickEvent event) {
        if(!eterAdvert.getListPlayerInAdvert().contains(event.getWhoClicked())) {
            return;
        }
        event.setCancelled(true);
    }
}
