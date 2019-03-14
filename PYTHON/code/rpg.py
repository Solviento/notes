"""
Bash RPG (Role Playing Game)

needs update

Description: The game has not been fully tested for weird bugs during
gameplay, in any case it is playable as I have played it several times.

In a slight twist, I have made it harder to play as a knight since most
people would typically pick the knight for their high attack and defense.

For this game it is easier to win if you pick a priest or wizard.

"""
import random
import sys

class Fox:

    name = "Ferocious Fox"
    hp = 2
    attack = 3
    speed = 7

    #Returns true if alive, good for while loops
    def checkHealth(self):
        if (Fox.hp < 1):
            return False
        else:
            return True
    #Removes health in battle sequences
    def removeHealth(self, damage):
        Fox.hp -= damage

    def REVIVE(self):
        Fox.hp = 2

class Goblin:

    name = "Groggy Goblin"
    hp = 4
    attack = 2
    speed = 4

    #Returns true if alive, good for while loops
    def checkHealth(self):
        if (Goblin.hp < 1):
            return False
        else:
            return True
    #Removes health in battle sequences
    def removeHealth(self, damage):
        Goblin.hp -= damage

    def REVIVE(self):
        Goblin.hp = 4


class Bandit:

    name = "Crooked Bandit"
    hp = 6
    attack = 4
    speed = 6

    #Returns true if alive, good for while loops
    def checkHealth(self):
        if (Bandit.hp < 1):
            return False
        else:
            return True
    #Removes health in battle sequences
    def removeHealth(self, damage):
        Bandit.hp -= damage

    def REVIVE(self):
        Bandit.hp = 6

class Bear:

    name = "Big Grizzly Bear"
    hp = 10
    attack = 4
    speed = 1

    #Returns true if alive, good for while loops
    def checkHealth(self):
        if (Bear.hp < 1):
            return False
        else:
            return True
    #Removes health in battle sequences
    def removeHealth(self, damage):
        Bear.hp -= damage

    def REVIVE(self):
        Bear.hp = 10

class Knight:

    """ Basic attributes, will change as story progresses """
    name = "Azel Blueshield"
    hp = 10
    attack = 4
    defense = 2
    speed = 2
    healing = 1

    #Regeneration
    def heal(self):
        if self.hp == 10:
            return ("HP already at max health")
        else:
            self.hp += Knight.healing
            if self.hp > 10:
                self.hp = 10

    #Returns true if alive, good for while loops
    def checkHealth(self):
        if self.hp < 1 :
            return False
        else:
            return True
    #Removes health during battle
    def removeHealth(self, damage):
        self.hp -= (damage - self.defense)

    """ All mods are part of story progression """
    def modHP(self):
        self.hp += 2

    def modAttack(self):
        self.attack += 3

    def modSpeed(self):
        self.speed += 1


class Wizard:

    """ Basic attributes, will change as story progresses """

    name = "Azel Bluestorm"
    hp = 10
    attack = 3
    defense = 2
    speed = 2
    healing = 2

    #Regeneration
    def heal(self):
        if Wizard.hp == 10:
            return ("HP already at max health")
        else:
            Wizard.hp += Wizard.healing
            if Wizard.hp > 10:
                Wizard.hp = 10

    #Returns true if alive, good for while loops
    def checkHealth(self):
        if Wizard.hp < 1 :
            return False
        else:
            return True
    #Removes health during battle
    def removeHealth(self, damage):
        Wizard.hp -= (damage - self.defense)

    """ All mods are part of story progression """
    def modHP(self):
        Wizard.hp += 2

    def modAttack(self):
        Wizard.attack += 3

    def modSpeed(self):
        Wizard.speed += 1


class Priest:

    name = "Azel Bluestaff"
    hp = 10
    attack = 2
    defense = 2
    speed = 2
    healing = 3

    def heal(self):
        if Priest.hp == 10:
            return ("HP already at max health")
        else:
            Priest.hp += Priest.healing
            if Priest.hp > 10:
                Priest.hp = 10

    def checkHealth(self):
        if Priest.hp < 1 :
            return False
        else:
            return True

    def removeHealth(self, damage):
        Priest.hp -= (damage - self.defense)

    def modHP(self):
        Priest.hp += 2

    def modAttack(self):
        Priest.attack += 3

    def modSpeed(self):
        Priest.speed += 3

class actions():

    #Test to see if this actually works with enemy as input
    def encounter(enemy):
        print ("A %s appeared! \n" %(enemy.name))

    #Running away only has a 10 percent chance of succesffully occuring
    def run_away():
        return random.random() < .1

    #Exit sequence for character's death
    def herodeath():
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
        print("Your character has died, game over\n")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
        sys.exit()

    def enemydeath():
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
        print("The enemy has been defeated! Good job.\n")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")

    #Encounter choices for battle sequences
    def choices():
        print("Press 1, 2, or 3\n")
        print("1 - Attack\n")
        print("2 - Heal\n")
        print("3 - Run (Only 10% of success)")

    def sword(self):

        sw = """
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM?+IM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMI??=?MM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMIIZ~IMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMII:ZIIMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMI??ZZIMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMII:$?IMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMII$Z:IMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMM7I?Z$IIMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMM7I:Z:IMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMM7IIZZIMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMM77IZZ:7MMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMII:ZZIMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMIII$$I?MMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMOI~$:IMMMMMMMMMMMMMMMMMMMM
MMMMMMZ7?MMMM7=Z$IIMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMD7I7IZZ~MMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMNZ$IZZ7MMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMOO8=7IMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMOOOO$$D$?MMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMOZZMMMMMMM$MMMMMMMMMMMMMMMMMMMMMMM
MMMMOZ$MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMO8ZZMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
$Z8OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
$7$$MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM"""
        print(sw)


#All acts in the story with text
class story():

    def intro(self):
        act = actions()
        act.sword()
        print("\nThe season has been long and brutal for your town. Autumn has come and")
        print("passed, yet, the harvest did not produce nearly enough for your family.")
        print("Your family begs you to go out and forage for food and supplies for the")
        print("coming winter. You know it is incredibly dangerous beyond the")
        print("safety of the town walls so you enlist the help of a freelance to aid")
        print("you in your journey. Their name is Azel and they will follow your command.\n")

        print("A friend tells you to head North so you pack your essentials and begin")
        print("your way down the beaten path through the Forgotten Forest.\n")
        print("After an hour of walking you hear the sounds of scurrying paws behind you.\n")
        print("\nYou've been followed by a small fox!\n")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")

    def act1():

        print("You come across a small abandoned outpost by the beaten down path.")
        print("As you explore the dusty remnants you find a small key.")
        print("You continue to look through the items and you see a small latch with label 'X'")
        print("As you begin to unlock the latch, you hear a small creak as the")
        print("entrance of the outpost is pushed open.\n")
        print("\nYou've been followed by a bandit!\n")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")

    def act2():
        print("You run off into the darkness battle scarred, but you manage to")
        print("shake off some of the pain. After half an hour of searching in the")
        print("damp forest you see patch of shrubery and beaten down redwood trees.")
        print("As you scavenge through the foilage you find a berry patch beneath")
        print("the fallen branches. Using your knapsack, you collect as many berries")
        print("as you possibly can. Once you begin to leave the area you hear several")
        print("snarling growls coming straight at you.\n\nYou've been spotted by a family of wild foxes!\n")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")

    def act3():
        print("With your recent victory against the fox clan you decide to trek across")
        print("the small creek ahead of you. The soft cries of the animals surrounding you")
        print("cause your body to feel goosebumps. Your pace quickens through the beaten")
        print("path and you quickly start looking for the next open area. You manage to find")
        print("a small opening and quickly spot a small, quiet lumber house. There appears to")
        print("be no one inside so you sneak your way in to find some supplies. You luckily find")
        print("small bundles of firewood that will help your family to survive the cold.")
        print("In the seconds after you pack up your newly acquired loot a loung BANG is heard")
        print("outside the lumber house.\n\nYou've been followed by a pair of bandits!\n")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")

    def act4():
        print("Your body has sustained several injuries during the journey but you continue to")
        print("to press on for your family. Slowly but surely you make your way through the heart")
        print("of the forest and see a small peaceful herd of animals drinking from a pond.")
        print("Although you do feel guilty for what is to come you take your bow and arrow")
        print("and begin hunting for food. You are quick and efficient leaving no room for")
        print("pain on the fallen animals. Once you start to collect your yield, a loud growl")
        print("comes at you from behind the trees.\n")
        print("You've been found by a grizzly bear!")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")

    def end():
        print("After having defeated the big grizzly bear you make your way back to")
        print("town. You follow your way back out of the forest as the day begins to")
        print("set once more. Azel, your trusty protector continues to aid you in your")
        print("path as he carries the loot of your recent wins.")
        print("Slowly but surely you make it out of the forest after having defeated")
        print("numerous enemies along the way.")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")
        print("\n")
        print("CONGRALUTATIONS!")
        print("Your family is now saved from the extreme hardship of the coming winter")
        print("with your newly found supplies. You should be very proud for achieving")
        print("this wonderful outome!\n")
        print("END")
        print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n")


class fight():

    def battle(self, hero, enemy):
        #Check speed values, then fire while loop until someone is dead
        #Main options: attack, heal, run

        print("A %s leans in forward to attack you.\n" %(enemy.name))

        while(enemy.checkHealth(enemy) and hero.checkHealth(hero)):
            #Hero attacks if speed is greater than enemy's
            if (hero.speed > enemy.speed):
                #Checks if hero dies after enemy's attack
                if (hero.hp < 1):
                    actions.herodeath()
                    break

                actions.choices()

                while True:
                    try:
                        #Test if user input is an int
                        user = int(input("\nChoice: "))
                    except ValueError:
                        print("Incorrect choice entered, try again.\n")
                        continue
                    #If user is some non-choice
                    if ( (user > 3) or (user < 1) ):
                        print("Non-choice entered. Try again.\n")
                        continue
                    else:
                        break

                if user == 1:
                    #Hero attacks and deals damage to enemy
                    enemy.removeHealth(hero, hero.attack)

                    print("\nAzel attacks %s for %d health points." %(enemy.name, hero.attack))
                    print("%s has %d hp left!\n" %(enemy.name, enemy.hp))

                    #If enemy is still alive after attack, enemy counterattacks
                    if (enemy.checkHealth(enemy)):
                        hero.removeHealth(hero, enemy.attack)
                        print("%s attacks Azel for %d health points!" %(enemy.name, (enemy.attack - hero.defense) ))
                        print("%s now has only %d hp remaining.\n" %(hero.name, hero.hp))

                    if (enemy.checkHealth(enemy) ==  False):

                        print("%s now has only %d hp remaining.\n" %(enemy.name, enemy.hp))
                        actions.enemydeath()
                        break
                if user == 2:
                    #Hero heals instantly
                    hero.heal(hero)
                    print("\n%s healed for %d health points! Azel is now at %d hp.\n" %(hero.name, hero.healing, hero.hp) )

                    #Enemy attacks
                    hero.removeHealth(hero, enemy.attack)
                    print("%s attacks Azel for %d health points!" %(enemy.name, (enemy.attack - hero.defense) ))
                    print("%s has only %d hp remaining.\n" %(hero.name, hero.hp))

                if user == 3:
                    if (actions.run_away()):
                        print("\nYou manage to flee into the darkness.")
                        break
                    else:
                        print("\nYou tripped on tree root and were unable to flee!\n")
                        #Enemy attacks
                        hero.removeHealth(hero, enemy.attack)
                        print("%s attacks Azel for %d health points!" %(enemy.name, (enemy.attack - hero.defense) ))
                        print("%s has only %d hp remaining.\n" %(hero.name, hero.hp))


            #Hero wins!
            if (enemy.checkHealth(enemy) == False):
                actions.enemydeath()

            #game over
            if (hero.checkHealth(hero) == False):
                actions.herodeath()

            #Hero is slower than enemy, loses health before counterattack
            if ( enemy.speed > hero.speed ) :

                hero.removeHealth(hero, enemy.attack)
                print("%s damaged %s for %d health points.\n%s has %d health points remaining.\n" %(enemy.name, hero.name, (enemy.attack - hero.defense), hero.name, hero.hp))

                #Checks if hero dies after enemy's attack
                if (hero.hp < 1):
                    actions.herodeath()
                    break

                actions.choices()

                while True:
                    try:
                        #Test if user input is an int
                        user = int(input("\nChoice: "))
                    except ValueError:
                        print("Incorrect choice entered, try again.\n")
                        continue
                    #If user is some non-choice
                    if ( (user > 3) or (user < 1) ):
                        print("Non-choice entered. Try again.\n")
                        continue
                    else:
                        break

                if user == 1:
                    #Hero attacks and deals damage to enemy
                    enemy.removeHealth(hero, hero.attack)

                    print("\nAzel attacks %s for %d health points." %(enemy.name, hero.attack))
                    print("%s has %d hp left!\n" %(enemy.name, enemy.hp))

                    if (enemy.checkHealth(enemy) ==  False):
                        actions.enemydeath()
                        break
                if user == 2:
                    hero.heal(hero)
                    print("%s healed for %d health points! Azel is now at %d hp." %(hero.name, hero.healing, hero.hp) )
                if user == 3:
                    if (actions.run_away()):
                        print("\nYou manage to flee into the darkness.")
                        break
                    else:
                        print("\nYou tripped on tree root and were unable to flee!\n")
                        #Enemy attacks
                        hero.removeHealth(hero, enemy.attack)
                        print("%s attacks Azel for %d health points!" %(enemy.name, (enemy.attack - hero.defense) ))
                        print("%s has only %d hp remaining.\n" %(hero.name, hero.hp))


            #Hero wins!
            if (enemy.checkHealth(enemy) == False):
                actions.enemydeath()

        enemy.REVIVE(enemy)

class playgame():

    hero = 0

    fox = Fox
    fox2 = Fox
    fox3 = Fox

    bandit = Bandit
    bandit2 = Bandit
    bandit3 = Bandit

    goblin = Goblin
    goblin2 = Goblin
    goblin3 = Goblin

    bear = Bear

    print("\nWelcome! This is an interactive role playing game loaded with ASCII art")
    print("and cool action sequences! Please read the text as you go along the game.")
    print("\nNow, what would you like to play as?\n")
    print("A Priest, Knight or a Wizard?\n")
    print("               Priest  | Knight  | Wizard ")
    print("Health Points:  10     |   10    |   10   ")
    print("Attack:          2     |    4    |    3   ")
    print("Defense:         1     |    3    |    2   ")
    print("Healing:         3     |    1    |    2   ")
    print("Speed:           2     |    2    |    2   ")

    print("\nPlease press\n1 - For Priest\n2 - For Knight\n3 - For Wizard")

    #User picks character type
    while True:
        try:
            #Test if user input is an int
            user = int(input("\nChoice: "))
        except ValueError:
            print("Incorrect choice entered, try again.\n")
            continue
        #If user is some non-choice
        if ( (user > 3) or (user < 1) ):
            print("Non-choice entered. Try again.")
            continue
        else:
            break
    if user == 1:
        hero = Priest
        print("\nYou have selected %s as your hero!" %(hero.name))
    if user == 2:
        hero = Knight
        print("\nYou have selected %s as your hero!" %(hero.name))
    if user == 3:
        hero = Wizard
        print("\nYou have selected %s as your hero!" %(hero.name))

    #Story object created, story tree starts
    storyarc = story()
    #First intro pops up
    storyarc.intro()

    fight_ = fight()
    fight_.battle(hero,fox)

    storyarc.act1()
    fight.battle(hero, bandit)

    storyarc.act2()
    fight_.battle(hero, fox2)
    fight_.battle(hero, fox3)

    storyarc.act3()
    fight_.battle(hero, bandit2)

    storyarc.act4()

    fight_.battle(hero, bear)

    storyarc.end()

def main():

    p = playgame

if __name__ == "__main__":
    main()
