package com.os.module2.task8;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.japi.function.Function;
import lombok.AllArgsConstructor;

public class AssemblerActor extends AbstractBehavior<AssemblerActor.Command> {

    private final Function<AssembleOfficePcCommand, Behavior<Command>> assembleOffice = request -> {
        PersonalComputer pc = request.pc;
        pc.setHdd("HDD Seagate 1Tb");
        pc.setRam("2Gb Samsung");
        pc.setMotherboard("Asus Socket 775");
        pc.setCpu("Intel Celeron 2Ghz");
        getContext().getLog().info("OFFICE PC ASSEMBLED {}", pc);
        return Behaviors.same();
    };

    private final Function<AssemblerGamerPcCommand, Behavior<Command>> assembleGamer = request -> {
        PersonalComputer pc = request.pc;
        pc.setHdd("SSD Seagate 1Tb");
        pc.setRam("32Gb Hynix");
        pc.setMotherboard("Asus Socket AM+");
        pc.setCpu("Intel Core i9");
        pc.setGpu("NVidia GeForce 9988");
        getContext().getLog().info("GAMER PC ASSEMBLED {}", pc);
        return Behaviors.same();
    };

    public static Behavior<AssemblerActor.Command> create() {
        return Behaviors.setup(AssemblerActor::new);
    }
    public AssemblerActor(ActorContext<AssemblerActor.Command> context) {
        super(context);
    }

    @Override
    public Receive<AssemblerActor.Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(AssembleOfficePcCommand.class, assembleOffice)
                .onMessage(AssemblerGamerPcCommand.class, assembleGamer)
                .build();
    }

    public interface Command {
    }

    @AllArgsConstructor
    public static class AssembleOfficePcCommand implements Command {
        public final PersonalComputer pc;
    }

    @AllArgsConstructor
    public static class AssemblerGamerPcCommand implements Command {
        public final PersonalComputer pc;
    }
}
