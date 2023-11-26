package com.buncord.tiltifybuns.commands;

import com.buncord.tiltifybuns.tiltify.Tiltify;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

public class TiltifyBunsCommand {

  public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
    dispatcher.register(
        Commands.literal("tiltifybuns")
                .requires((player) -> player.hasPermission(Commands.LEVEL_MODERATORS))
                .then(Commands.literal("campaign")
                              .then(Commands.argument("campaignID", StringArgumentType.word())
                              .executes(TiltifyBunsCommand::campaign)))
                .then(Commands.literal("login")
                              .then(Commands.argument("clientID", StringArgumentType.word())
                              .then(Commands.argument("clientSecret", StringArgumentType.word())
                              .executes(TiltifyBunsCommand::login))))
    );
  }

  private static int campaign(CommandContext<CommandSourceStack> command){
    String campaignID = StringArgumentType.getString(command, "campaignID").toLowerCase();

    Tiltify.campaign(campaignID);

    command.getSource().sendSuccess(
        new TextComponent("TiltifyBuns watching campaign: " + campaignID),
        false
    );

    return Command.SINGLE_SUCCESS;
  }

  private static int login(CommandContext<CommandSourceStack> command){
    String clientID = StringArgumentType.getString(command, "clientID");
    String clientSecret = StringArgumentType.getString(command, "clientSecret");

    Tiltify.login(clientID, clientSecret);

    command.getSource().sendSuccess(
        new TextComponent("TiltifyBuns logged in!"),
        false
    );

    return Command.SINGLE_SUCCESS;
  }

}
