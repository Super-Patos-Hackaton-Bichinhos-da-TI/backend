package com.api.superpatos.enums;

import lombok.Getter;

@Getter
public enum Pet {
    DUCK("Duck"),
    DOG("Dog"),
    CAT("Cat"),
    ELEPHANT("Elephant"),
    LION("Lion"),
    TIGER("Tiger"),
    BEAR("Bear"),
    WOLF("Wolf"),
    FOX("Fox"),
    DEER("Deer"),
    GIRAFFE("Giraffe"),
    ZEBRA("Zebra"),
    KANGAROO("Kangaroo"),
    PANDA("Panda"),
    KOALA("Koala"),
    MONKEY("Monkey"),
    GORILLA("Gorilla"),
    CHIMPANZEE("Chimpanzee"),
    RHINOCEROS("Rhinoceros"),
    HIPPOPOTAMUS("Hippopotamus"),
    CROCODILE("Crocodile"),
    ALLIGATOR("Alligator"),
    EAGLE("Eagle"),
    HAWK("Hawk"),
    OWL("Owl"),
    PARROT("Parrot"),
    PEACOCK("Peacock"),
    PENGUIN("Penguin"),
    FLAMINGO("Flamingo"),
    SWAN("Swan"),
    DOLPHIN("Dolphin"),
    WHALE("Whale"),
    SHARK("Shark"),
    OCTOPUS("Octopus"),
    TURTLE("Turtle"),
    SNAKE("Snake"),
    LIZARD("Lizard"),
    FROG("Frog"),
    TOAD("Toad"),
    RABBIT("Rabbit"),
    SQUIRREL("Squirrel"),
    BAT("Bat"),
    MOUSE("Mouse"),
    RAT("Rat"),
    HAMSTER("Hamster"),
    HEDGEHOG("Hedgehog"),
    HORSE("Horse"),
    DONKEY("Donkey"),
    COW("Cow"),
    SHEEP("Sheep"),
    GOAT("Goat");

    private final String pet;

    Pet(String pet) {
        this.pet = pet;
    }
}
