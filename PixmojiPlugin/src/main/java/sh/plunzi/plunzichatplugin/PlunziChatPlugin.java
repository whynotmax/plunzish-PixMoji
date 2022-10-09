package sh.plunzi.plunzichatplugin;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sh.plunzi.plunzichatplugin.chatSending.ChatHandler;
import sh.plunzi.plunzichatplugin.commands.*;
import sh.plunzi.plunzichatplugin.commands.tabCompletion.*;
import sh.plunzi.plunzichatplugin.utils.DatabaseManager;
import sh.plunzi.plunzichatplugin.utils.FileManager;
import sh.plunzi.plunzichatplugin.listeners.ChatListener;
import sh.plunzi.plunzichatplugin.listeners.JoinLeaveListener;
import sh.plunzi.plunzichatplugin.pixmojiData.Pixmojis;

import net.kyori.adventure.text.Component;
import sh.plunzi.plunzichatplugin.utils.OtherUtils;

public final class PlunziChatPlugin extends JavaPlugin {

    public static Pixmojis PIXMOJIS;
    public static ChatHandler CHAT_HANDLER;
    public static FileManager FILE_MANAGER;
    public static DatabaseManager DATABASE_MANAGER;
    public static PlunziChatPlugin INSTANCE;
    public static String PLUNZISH_NAMESPACE = "plunzish";

    public static Component PREFIX;

    public static final Key PIXMOJI_FONT = Key.key(PLUNZISH_NAMESPACE, "pixmojis");
    public static final Key PIXMOJI_FONT_LARGE = Key.key(PLUNZISH_NAMESPACE, "pixmojis_large");
    public static final Key PIXMOJI_FONT_TRANSPARENT = Key.key(PLUNZISH_NAMESPACE, "transparent");
    public static final Key CHAT_PING_SOUND = Key.key(PlunziChatPlugin.PLUNZISH_NAMESPACE, "chat.ping");

    @Override
    public void onEnable() {
        // Plugin startup logic
        register();
        Bukkit.getConsoleSender().sendMessage("\u00a76Pixmoji Plugin loaded \u00a7a:" + PIXMOJIS.getRandom().getName() + ":");

        PREFIX = OtherUtils.buildComponent("[Plunzish]", Color.fromRGB(0xffc400), Color.fromRGB(0xae00d9));
        PREFIX = PREFIX.append(Component.text(" ").color(TextColor.color(0x666666)));
    }


    //THE LIST OF WHAT I NEED TO DO
    //✅ Pings
    //✅ Pingsound
    //
    //✅ Nachrichten ^_^
    //✅ dms
    //
    //TODO * + ,
    //
    //TODO Broadcast
    //
    //TODO Database <-
    //
    //TODO Friend system    <---
    // - responde /r
    // - accept all
    // - decline all
    // - lock
    //
    //TODO Partysystem
    // - Partychat
    //TODO Formatting


    private void register() {
        FILE_MANAGER = new FileManager();
        PIXMOJIS = new Pixmojis();
        CHAT_HANDLER = new ChatHandler();
        DATABASE_MANAGER = new DatabaseManager();
        INSTANCE = this;

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new JoinLeaveListener(), this);

        Bukkit.getPluginCommand("msg").setExecutor(new PrivateMessageCommand());
        Bukkit.getPluginCommand("msg").setTabCompleter(new PrivateMessageTabCompletion());
        Bukkit.getPluginCommand("setcensor").setExecutor(new SetCensorlevelCommand());
        Bukkit.getPluginCommand("setcensor").setTabCompleter(new SetCensorTabCompletion());
        Bukkit.getPluginCommand("debug").setExecutor(new DebuggingCommand());
        Bukkit.getPluginCommand("debug").setTabCompleter(new DebuggingTabCompletion());
        Bukkit.getPluginCommand("op").setExecutor(new OpCommand());
        Bukkit.getPluginCommand("op").setTabCompleter(new OpCommandsTabCompletion());
        Bukkit.getPluginCommand("deop").setExecutor(new DeopCommand());
        Bukkit.getPluginCommand("deop").setTabCompleter(new OpCommandsTabCompletion());
        Bukkit.getPluginCommand("clearmydata").setExecutor(new ClearDataCommand());
        Bukkit.getPluginCommand("clearmydata").setTabCompleter(new NoTabCompletion());
        Bukkit.getPluginCommand("broadcast").setExecutor(new BroadcastCommand());
        Bukkit.getPluginCommand("broadcast").setTabCompleter(new MessageTabCompletion());
        Bukkit.getPluginCommand("t").setExecutor(new TextCommand());
        Bukkit.getPluginCommand("t").setTabCompleter(new MessageTabCompletion());
        Bukkit.getPluginCommand("r").setExecutor(new ResponseCommand());
        Bukkit.getPluginCommand("r").setTabCompleter(new MessageTabCompletion());
        Bukkit.getPluginCommand("friends").setExecutor(new FriendsCommand());
        Bukkit.getPluginCommand("friends").setTabCompleter(new FriendsTabCompletion());

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("\u00a76Pixmoji Plugin unloaded \u00a7c:" + PIXMOJIS.getRandom().getName() + ":");
    }
}
