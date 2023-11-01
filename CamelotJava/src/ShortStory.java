import java.util.HashMap;

import com.actions.ActionSequence;
import com.actions.Attack;
import com.actions.Create;
import com.actions.CreateEffect;
import com.actions.Dance;
import com.actions.Die;
import com.actions.Drink;
import com.actions.EnableInput;
import com.actions.Exit;
import com.actions.Give;
import com.actions.OpenFurniture;
import com.actions.SetCameraFocus;
import com.actions.SetDialog;
import com.actions.SetNarration;
import com.actions.ShowDialog;
import com.actions.ShowMenu;
import com.actions.Take;
import com.actions.Wave;
import com.storygraph.ActionMap;
import com.storygraph.INode;
import com.storygraph.Node;
import com.entities.IEntity;
import com.entities.Item;
import com.entities.Item.Items;
import com.entities.Place;
import com.entities.Things.ThingNames;
import com.playerInput.ActionChoice;
import com.playerInput.ActionChoice.Icons;
import com.playerInput.SelectionChoice;
import com.sequences.CharacterCreation;
import com.actions.Position;
import com.actions.CreateEffect.Effect;
import com.entities.Character;
import com.entities.Furniture;


public class ShortStory implements IStory{
	
	public enum NodeLabels{
		Init, OpenCloset, PutOnCloak, TtoCityKillEnemy, TakePotion, DrinkPotionKillEnemy, DancePotionKillEnemy, enemydead, ExitDoor, AttackNPCDie, TalktoNPC, DanceKilledByEnemy, AskHelpKilledbyEnemy, RefuseHelpKilledbyNPC, BedroomSequence, potionsequence, npcsequence, talkwithnpcsequence}
	public enum ChoiceLabels {BecomeHero, BecomeWitch, AttackNPC, TalktoNPC, DancewNPC, NPCAskHelp, NPCRefuseHelp, Dance }
	public enum Characternames {Charlotte, NPC, enemy, villager, witch}
	public enum Placenames {bedroom, city, camp}
	public enum Itemnames {cloak, goldcup, bluepotion, sword, enchantedBook}
	
	HashMap<Characternames, Character> characterlist = new HashMap<>();
	HashMap<Placenames, Place> placelist = new HashMap<>();
	HashMap<Itemnames, Item> itemlist = new HashMap<>();
	
	
	public ShortStory() {
		getThings();
	}
	
	private enum ActionNames{
		Take, Exit, Open, Teleport,Drink,Dance,Talk,AskHelp, RefuseHelp, GameOver, Discuss, Celebrate, PotionSeq, DiscussMore , NPCSeq
	}
	
	private Place bedroom;
	private Item cloak;
	private Place city;
	private Item bluepotion;
	private Character NPC;
	private Character Charlotte;
	
	@Override
	public INode getRoot() {
		var initNode = new Node(NodeLabels.Init.toString());
		var bedroomSequenceInitNode = new Node(NodeLabels.BedroomSequence.Init.toString());
		var openClosetNode = new Node(NodeLabels.OpenCloset.toString());
		var putOnCloakNode = new Node(NodeLabels.PutOnCloak.toString());
		var toCityKillEnemyNode = new Node(NodeLabels.TtoCityKillEnemy.toString());
		var takePotionNode = new Node(NodeLabels.TakePotion.toString());
		var drinkPotionKillEnemyNode = new Node(NodeLabels.DrinkPotionKillEnemy.toString());
		var dancePotionKillEnemyNode = new Node(NodeLabels.DancePotionKillEnemy.toString());
		var exitDoorNode = new Node(NodeLabels.ExitDoor.toString());
		var attackNPCDieNode = new Node(NodeLabels.AttackNPCDie.toString());
		var talkToNPCNode = new Node(NodeLabels.TalktoNPC.toString());
		var danceKilledByEnemyNode = new Node(NodeLabels.DanceKilledByEnemy.toString());
		var askHelpKilledbyEnemyNode = new Node(NodeLabels.AskHelpKilledbyEnemy.toString());
		var refuseHelpKilledbyNPCNode = new Node(NodeLabels.RefuseHelpKilledbyNPC.toString());
		var enemyDeadNode = new Node(NodeLabels.enemydead.toString());
		var potionSequenceNode = new Node(NodeLabels.potionsequence.toString());
		var npcSequenceNode = new Node(NodeLabels.npcsequence.toString());
		var talkWithNpcSequenceNode = new Node(NodeLabels.talkwithnpcsequence.toString());

		 initNode.addChild(
	        new ActionChoice(
	            ActionNames.Exit.toString(),
	            bedroom.getFurniture("Door"),
	            Icons.door,
	            "Leave house",
	            true),
	        exitDoorNode
	    );

	   
	    bedroomSequenceInitNode.addChild(
	        new ActionChoice(
	            ActionNames.Open.toString(),
	            bedroom.getFurniture("Closet"),
	            Icons.woodendoor,
	            "Open the closet",
	            true),
	        openClosetNode
	    );

	
	    openClosetNode.addChild(
	        new ActionChoice(ActionNames.Take.toString(),
	        cloak,
	        Icons.armour,
	        "Put on the cloak",
	        true),
	        putOnCloakNode
	    );


	    putOnCloakNode.addChild(
	        new ActionChoice(
            ActionNames.Teleport.toString(),
            cloak,
            Icons.city,
            "Teleport to the city",
            true),
	        toCityKillEnemyNode
	    );


	    toCityKillEnemyNode.addChild(
	        new ActionChoice(
	            ActionNames.Take.toString(),
	            bluepotion,
	            Icons.potion,
	            "Take the potion",
	            true),
	        takePotionNode
	    );


	    takePotionNode.addChild(
	        new ActionChoice(
	            ActionNames.Drink.toString(),
	            bluepotion,
	            Icons.potion,
	            "Drink the potion and fight",
	            true),
	        drinkPotionKillEnemyNode
	    );

	    takePotionNode.addChild(
	        new ActionChoice(
	            ActionNames.Dance.toString(),
	            bluepotion,
	            Icons.kneel,
	            "Dance with the potion",
	            true),
	        dancePotionKillEnemyNode
	    );


	
		 drinkPotionKillEnemyNode.addChild(
		     new ActionChoice(
		         ActionNames.Talk.toString(),
		         NPC,
		         Icons.talk,
		         "Talk to NPC",
		         true),
		     talkToNPCNode
		 );

		 dancePotionKillEnemyNode.addChild(
		     new ActionChoice(
		         ActionNames.AskHelp.toString(),
		         NPC,
		         Icons.kneel,
		         "Ask for help",
		         true),
		     askHelpKilledbyEnemyNode
		 );
	
		 dancePotionKillEnemyNode.addChild(
		     new ActionChoice(
		         ActionNames.RefuseHelp.toString(),
		         NPC,
		         Icons.arrest,
		         "Refuse help",
		         true),
		     refuseHelpKilledbyNPCNode
		 );
		 

		 exitDoorNode.addChild(
		     new ActionChoice(
		         ActionNames.GameOver.toString(),
		         null, 
		         Icons.exit,
		         "Game Over",
		         true),
		     null
		 );


		 attackNPCDieNode.addChild(
		     new ActionChoice(
		         ActionNames.GameOver.toString(),
		         null,
		         Icons.exit,
		         "Game Over",
		         true),
		     null
		 );


		 talkToNPCNode.addChild(
		     new ActionChoice(
		         ActionNames.Discuss.toString(),
		         NPC,
		         Icons.talk,
		         "Discuss your quest",
		         true),
		     talkWithNpcSequenceNode
		 );


		 danceKilledByEnemyNode.addChild(
		     new ActionChoice(
		         ActionNames.GameOver.toString(),
		         null,
		         Icons.exit,
		         "Game Over",
		         true),
		     null
		 );


		 askHelpKilledbyEnemyNode.addChild(
		     new ActionChoice(
		         ActionNames.GameOver.toString(),
		         null,
		         Icons.exit,
		         "Game Over",
		         true),
		     null
		 );


		 refuseHelpKilledbyNPCNode.addChild(
		     new ActionChoice(
		         ActionNames.GameOver.toString(),
		         null,
		         Icons.exit,
		         "Game Over",
		         true),
		     null 
		 );


		 enemyDeadNode.addChild(
		     new ActionChoice(
		         ActionNames.Celebrate.toString(),
		         Charlotte,
		         Icons.drink,
		         "Celebrate your victory",
		         true),
		     null 
		 );


		 potionSequenceNode.addChild(
		     new ActionChoice(
		         ActionNames.PotionSeq.toString(),
		         bluepotion,
		         Icons.potion,
		         "Continue with potion",
		         true),
		     null 
		 );


		 npcSequenceNode.addChild(
		     new ActionChoice(
		         ActionNames.NPCSeq.toString(),
		         NPC,
		         Icons.boot,
		         "Continue with NPC",
		         true),
		     null
		 );

	
		 talkWithNpcSequenceNode.addChild(
		     new ActionChoice(
		         ActionNames.DiscussMore.toString(),
		         NPC,
		         Icons.talk,
		         "Discuss more",
		         true),
		     null
		 );
		 
			
		var root = new Node(NodeLabels.Init.toString());
		root.addChild(new SelectionChoice("Init"), initNode);
			
		return root;
	}

	@Override
	public void getThings() {
		characterlist.put(Characternames.Charlotte, new Character(ThingNames.Charlotte, Character.BodyTypes.A));
		characterlist.put(Characternames.NPC, new Character(ThingNames.NPC, Character.BodyTypes.A));
		characterlist.put(Characternames.enemy, new Character(ThingNames.enemy, Character.BodyTypes.A));
		characterlist.put(Characternames.villager, new Character(ThingNames.villager, Character.BodyTypes.A));
		characterlist.put(Characternames.witch, new Character(ThingNames.witch, Character.BodyTypes.A));
		
		placelist.put(Placenames.bedroom, new Place(ThingNames.Bedroom, Place.Places.CastleBedroom));
		placelist.put(Placenames.city, new Place(ThingNames.city, Place.Places.City));
		placelist.put(Placenames.camp, new Place(ThingNames.camp, Place.Places.Camp));
		
		itemlist.put(Itemnames.sword, new Item(ThingNames.Sword, Items.Sword));
		itemlist.put(Itemnames.cloak, new Item(ThingNames.Cloak, Items.PurpleCloth));
		itemlist.put(Itemnames.goldcup, new Item(ThingNames.Goldcup, Items.GoldCup));
		itemlist.put(Itemnames.bluepotion, new Item(ThingNames.Bluepotion, Items.BluePotion));
		itemlist.put(Itemnames.enchantedBook, new Item(ThingNames.EnchantedBook, Items.SpellBook));
	}
	
	public ActionMap getMap() {
		var map = new ActionMap();
		map.add(NodeLabels.Init.toString(), getInitSequence());
		map.add(NodeLabels.BedroomSequence.Init.toString(), getBedroomSequence());
		map.add(NodeLabels.OpenCloset.toString(), getCloset());
		map.add(NodeLabels.PutOnCloak.toString(), getCloak());
		map.add(NodeLabels.TtoCityKillEnemy.toString(), getTeleportCity());
		map.add(NodeLabels.TakePotion.toString(), getPotion());
		map.add(NodeLabels.DrinkPotionKillEnemy.toString(), getDrinkPotion());
		map.add(NodeLabels.DancePotionKillEnemy.toString(), getDanceWithPotion());
		map.add(NodeLabels.ExitDoor.toString(), getExitFrontDoor());
		map.add(NodeLabels.AttackNPCDie.toString(), getAttackNPC());
		map.add(NodeLabels.TalktoNPC.toString(), getTalktoNPC());
		map.add(NodeLabels.DanceKilledByEnemy.toString(), getDanceTogether());
		map.add(NodeLabels.AskHelpKilledbyEnemy.toString(), getAskForHelp());
		map.add(NodeLabels.RefuseHelpKilledbyNPC.toString(), getRefuseHelp());
		map.add(NodeLabels.enemydead.toString(), getEnenemydead());
		map.add(NodeLabels.potionsequence.toString(), getPotionSequence());
		map.add(NodeLabels.npcsequence.toString(), getNpcSequence());
		map.add(NodeLabels.talkwithnpcsequence.toString(), getTalkWithNpcSequence());
		return map;
	}
	
	
	
	
	private ActionSequence getInitSequence() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var bedroom = placelist.get(Placenames.bedroom);
		var city = placelist.get(Placenames.city);
		var cloak = itemlist.get(Itemnames.cloak);
		var goldcup = itemlist.get(Itemnames.goldcup);
		var bluepotion = itemlist.get(Itemnames.bluepotion);
		
		var sequence = new ActionSequence();
		sequence.add(new Create<Place>(bedroom));
		sequence.add(new Create<Character>(charlotte));
		sequence.add(new Position(charlotte, bedroom));
		sequence.add(new Create<Place>(city));
		sequence.add(new Create<Item>(cloak));
		sequence.add(new Position(cloak, bedroom, "Closet"));
		sequence.add(new Create<Item>(goldcup));
		sequence.add(new Create<Item>(bluepotion));
		sequence.add(new SetCameraFocus(charlotte));
		sequence.add(new SetNarration("The adventure begins now..."));
		sequence.add(new ShowMenu(true));
		sequence.add(new SetCameraFocus(charlotte));
		return sequence;
		
	}
	
	private ActionSequence getBedroomSequence() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var sword = itemlist.get(Itemnames.sword);
		
        var sequence = new ActionSequence();
        sequence.add(new Create<Character>(charlotte));
        sequence.add(new Create<Item>(sword));
        sequence.add(new Take(charlotte, sword));
        sequence.add(new Wave(charlotte));
        sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Aura));
        sequence.add(new SetNarration("Tom finds a mystical sword..."));
        return sequence;
    }

	
	private ActionSequence getCloset() {
		var sequence = new ActionSequence();
		//opens furniture (how to?)
		//sequence.add(new OpenFurniture(charlotte, "Closet"));
		return sequence;
	}
	
	private ActionSequence getCloak() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var cloak = itemlist.get(Itemnames.cloak);
		
		var sequence = new ActionSequence();
		sequence.add(new Create<Item>(cloak));
		sequence.add(new Take(charlotte, cloak));
		return sequence;
	}
	
	private ActionSequence getTeleportCity() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var city = placelist.get(Placenames.city);
		var enemy = characterlist.get(Characternames.enemy);
		var goldcup = itemlist.get(Itemnames.goldcup);
		
		var sequence = new ActionSequence();
		sequence.add(new Create<Character>(charlotte));
		sequence.add(new Create<Character>(enemy));
		sequence.add(new Create<Item>(goldcup));
		sequence.add(new Create<Place>(city));
		sequence.add(new Position(charlotte, city));
		sequence.add(new SetCameraFocus(charlotte));
		sequence.add(new Attack(charlotte, enemy, true));
		sequence.add(new Take(charlotte, goldcup));
		sequence.add(new Dance(charlotte));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Aura));
		return sequence;
	}
	
	
	private ActionSequence getPotion() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var bluepotion = itemlist.get(Itemnames.bluepotion);
		
		var sequence = new ActionSequence();
		sequence.add(new Create<Item>(bluepotion));
		sequence.add(new Take(charlotte, bluepotion ));
		return sequence;
	}
	
	private ActionSequence getDrinkPotion() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var city = placelist.get(Placenames.city);
		var enemy = characterlist.get(Characternames.enemy);
		var goldcup = itemlist.get(Itemnames.goldcup);
		
		var sequence = new ActionSequence();
		sequence.add(new Drink(charlotte));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Poof));
		sequence.add(new Position(charlotte, city));
		sequence.add(new SetCameraFocus(charlotte));
		sequence.add(new Attack(charlotte, enemy, true));
		sequence.add(new Die(enemy));
		sequence.add(new Take(charlotte, goldcup));
		sequence.add(new Dance(charlotte));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Aura));
		return sequence;
	}
	
	private ActionSequence getDanceWithPotion() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var city = placelist.get(Placenames.city);
		var enemy = characterlist.get(Characternames.enemy);
		var goldcup = itemlist.get(Itemnames.goldcup);
		
		var sequence = new ActionSequence();
		sequence.add(new Dance(charlotte));
		sequence.add(new Create<Character>(charlotte));
		sequence.add(new Create<Place>(city));
		sequence.add(new Create<Character>(enemy));
		sequence.add(new Create<Item>(goldcup));
		sequence.add(new Position(charlotte, city));
		sequence.add(new SetCameraFocus(charlotte));
		sequence.add(new Attack(charlotte, enemy, true));
		sequence.add(new Die(enemy));
		sequence.add(new Take(charlotte, goldcup));
		sequence.add(new Dance(charlotte));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Aura));
		return sequence;
	}
	
	private ActionSequence getExitFrontDoor() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var camp = placelist.get(Placenames.camp);
		var bedroom = placelist.get(Placenames.bedroom);
		
		var sequence = new ActionSequence();
		sequence.add(new Exit(charlotte, bedroom.getFurniture("Door"), true));
		sequence.add(new Create<Place>(camp));
		sequence.add(new Position(charlotte, camp));
		sequence.add(new SetCameraFocus(charlotte));
		return sequence;
	}
	
	private ActionSequence getAttackNPC() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var NPC = characterlist.get(Characternames.NPC);
		
		var sequence = new ActionSequence();
		sequence.add(new Attack(charlotte, NPC, true));
		sequence.add(new CreateEffect(NPC, CreateEffect.Effect.Blackflame));
		sequence.add(new Attack(NPC, charlotte, true));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Death));
		sequence.add(new Die(charlotte));
		return sequence;
	}
	
	private ActionSequence getTalktoNPC() {
		var sequence = new ActionSequence();
		sequence.add(new ShowDialog(true));
		sequence.add(new EnableInput(true));
		return sequence;
	}
	
	private ActionSequence getDanceTogether() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var NPC = characterlist.get(Characternames.NPC);
		var enemy = characterlist.get(Characternames.enemy);
		var city = placelist.get(Placenames.city);
		
		var sequence = new ActionSequence();
		sequence.add(new Dance(charlotte));
		sequence.add(new Dance(NPC));
		sequence.add(new Position(charlotte, city));
		sequence.add(new SetCameraFocus(charlotte));
		sequence.add(new Attack(charlotte, enemy, false));
		sequence.add(new Attack(enemy, charlotte, true));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Death));
		sequence.add(new Die(charlotte));
		return sequence;
	}
	
	private ActionSequence getAskForHelp() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var enemy = characterlist.get(Characternames.enemy);
		var city = placelist.get(Placenames.city);
		
		var sequence = new ActionSequence();
		sequence.add(new Position(charlotte, city));
		sequence.add(new SetCameraFocus(charlotte));
		sequence.add(new SetDialog("Do you want help?"));
		sequence.add(new SetDialog("[Accept|Yes!"));
		sequence.add(new SetDialog("[Reject|No!"));
		sequence.add(new ShowDialog(true));
		
		sequence.add(new Attack(charlotte, enemy, false));
		sequence.add(new Attack(enemy, charlotte, true));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Death));
		sequence.add(new Die(charlotte));
		return sequence;
	}
	
	private ActionSequence getRefuseHelp() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var NPC = characterlist.get(Characternames.NPC);
		
		var sequence = new ActionSequence();
		sequence.add(new CreateEffect(NPC, CreateEffect.Effect.Blackflame));
		sequence.add(new Attack(NPC, charlotte, true));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Death));
		sequence.add(new Die(charlotte));
		return sequence;
	}
	
	private ActionSequence getPotionSequence() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var witch = characterlist.get(Characternames.witch);
		
        var sequence = new ActionSequence();
        sequence.add(new Wave(charlotte));
        sequence.add(new Wave(witch));
        sequence.add(new SetNarration("Yay swordddd!"));
        sequence.add(new CreateEffect(witch, CreateEffect.Effect.Aura));
        return sequence;
    }
	
	private ActionSequence getEnenemydead() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var sword = itemlist.get(Itemnames.sword);
		var enemy = characterlist.get(Characternames.enemy);
		
        var sequence = new ActionSequence();
        sequence.add(new SetCameraFocus(charlotte));
        sequence.add(new Take(charlotte, sword));
        sequence.add(new Attack(charlotte, enemy, true));
        sequence.add(new Dance(charlotte));
        sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Magic));
        return sequence;
    }

	private ActionSequence getNpcSequence() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var villager = characterlist.get(Characternames.villager);
		var bluepotion = itemlist.get(Itemnames.bluepotion);
		
		
        var sequence = new ActionSequence();
        sequence.add(new Give(charlotte, bluepotion, villager));
        sequence.add(new SetNarration("A token of gratitude"));
        sequence.add(new CreateEffect(villager, CreateEffect.Effect.Magic));
        return sequence;
    }
	
	private ActionSequence getTalkWithNpcSequence() {
		var charlotte = characterlist.get(Characternames.Charlotte);
		var enemy = characterlist.get(Characternames.enemy);
		var villager = characterlist.get(Characternames.villager);
		
        var sequence = new ActionSequence();
        sequence.add(new Dance(charlotte));
        sequence.add(new Dance(villager));
        sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Happy));
        sequence.add(new CreateEffect(villager, CreateEffect.Effect.Happy));
        return sequence;
    }



	

}
