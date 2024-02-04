package fr.etercube.eteradvert.command;

import fr.etercube.eteradvert.EterAdvert;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandAdvert implements CommandExecutor {
    private EterAdvert eterAdvert;
    public CommandAdvert(EterAdvert eterAdvert) {
        this.eterAdvert = eterAdvert;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {return false;}

        Player player = (Player) commandSender;

        HashMap<String, String> permissionList = eterAdvert.getPermissionList();
        HashMap<String, String> messageList = eterAdvert.getMessageList();

        // - Verification Permission

        if(!player.hasPermission(permissionList.get("advertuse"))) {
            player.sendMessage(messageList.get("nopermission"));
            return false;
        }

        // - Vérification Format Commande

        if(strings.length < 1) {
            player.sendMessage(messageList.get("messageformat"));
            return false;
        }

        // - Vérification du joueurs ciblés

        if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))) {
            player.sendMessage(messageList.get("playernotonline"));
            return false;
        }

        // - Ouverture de l'inventaire

        Inventory inventory = Bukkit.createInventory(null, 9, "Advert");

        Player targetPlayer = Bukkit.getPlayer(strings[0]);

        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            str.append(strings[i] + " ");
        }
        String string = str.toString();

        itemMeta.setDisplayName(string.replace(strings[0], "").replace("&", "§"));
        itemStack.setItemMeta(itemMeta);

        inventory.setItem(4, itemStack);

        targetPlayer.openInventory(inventory);

        // - Stockage du joueur

        ArrayList<Player> listPlayerInAdvert = eterAdvert.getListPlayerInAdvert();
        listPlayerInAdvert.add(targetPlayer);
        eterAdvert.setListPlayerInAdvert(listPlayerInAdvert);



        return false;
    }
}
