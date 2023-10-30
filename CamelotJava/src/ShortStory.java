import com.actions.ActionSequence;
import com.actions.Attack;
import com.actions.Create;
import com.actions.CreateEffect;
import com.actions.Dance;
import com.actions.Die;
import com.actions.Drink;
import com.actions.EnableInput;
import com.actions.Exit;
import com.actions.OpenFurniture;
import com.actions.SetCameraFocus;
import com.actions.ShowDialog;
import com.actions.ShowMenu;
import com.actions.Take;
import com.storygraph.ActionMap;
import com.storygraph.INode;
import com.storygraph.Node;
import com.entities.IEntity;
import com.entities.Item;
import com.entities.Item.Items;
import com.entities.Place;
import com.entities.Things.ThingNames;
import com.sequences.CharacterCreation;
import com.actions.Position;
import com.actions.CreateEffect.Effect;
import com.entities.Character;


public class ShortStory implements IStory{
	private Character charlotte;
	private Character NPC;
	private Character enemy;
	private Character witch;
	private Character villager;
	private Place bedroom;
	private Place city;
	private Place camp;
	private Item cloak;
	private Item goldcup;
	private Item bluepotion; 
	private Item sword;
	private Item enchantedBook;
	
	
	@Override
	public INode getRoot() {
		return new Node("root");
	}

	@Override
	public void getThings() {
		charlotte = new Character(ThingNames.Charlotte, Character.BodyType.A);
		NPC = new Character(ThingNames.NPC, Character.BodyType.A);
		enemy = new Character(ThingNames.enemy, Character.BodyType.A);
		witch = new Character(ThingNames.Witch, Character.BodyType.A);
		villager = new Character(ThingNames.Villager, Character.BodyType.A);
		bedroom = new Place(ThingNames.Bedroom, Place.Places.CastleBedroom);
		city = new Place(ThingNames.City, Place.Places.City);
		sword = new Item(ThingNames.Sword, Items.Sword);
		cloak = new Item(ThingNames.Cloak, Items.PurpleCloth);
		goldcup = new Item(ThingNames.Goldcup, Items.GoldCup);
		bluepotion = new Item(ThingNames.Bluepotion, Items.BluePotion);
		enchantedBook = new Item(ThingNames.EnchantedBook, Items.SpellBook);
		
	}
	
	private enum NodeLabels{
		Init, OpenCloset, PutOnCloak, TtoCityKillEnemy, TakePotion, DrinkPotionKillEnemy, DancePotionKillEnemy, enemydead, ExitDoor, AttackNPCDie, TalktoNPC, DanceKilledByEnemy, AskHelpKilledbyEnemy, RefuseHelpKilledbyNPC, BedroomSequence
	}
	
	
	
	public ActionMap getMap() {
		var map = new ActionMap();
		map.add(NodeLabels.Init.toString(), getInitSequence());
		map.add(NodeLabels.BedroomSequence.Init.toString(), getBedroomSequence(), getBedroomSequence());
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
		return map;
	}
	
	private ActionSequence getInitSequence() {
		var sequence = new ActionSequence();
		//sequence.combineWith(new CharacterCreation(charlotte));
		sequence.add(new Create<Place>(bedroom));
		sequence.add(new Position(charlotte, bedroom));
		sequence.add(new Create<Place>(city));
		sequence.add(new Create<Item>(cloak));
		sequence.add(new Position(cloak, bedroom, "Closet"));
		sequence.add(new Create<Item>(goldcup));
		sequence.add(new Create<Item>(bluepotion));
		sequence.add(new SetCameraFocus(charlotte));
		sequence.add(new SetNarration("The adventure begins now..."));
		sequence.add(new ShowMenu(true));
		return sequence;
		
	}
	
	private ActionSequence getBedroomSequence() {
        var sequence = new ActionSequence();
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
		var sequence = new ActionSequence();
		sequence.add(new Take(charlotte, cloak));
		return sequence;
	}
	
	private ActionSequence getTeleportCity() {
		var sequence = new ActionSequence();
		sequence.add(new Position(charlotte, city));
		sequence.add(new SetCameraFocus(charlotte));
		//create enemy character
		//sequence.add(new Create<Character>(enemy));
		sequence.add(new Attack(charlotte, enemy, true));
		sequence.add(new Take(charlotte, goldcup));
		sequence.add(new Dance(charlotte));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Aura));
		return sequence;
	}
	
	
	private ActionSequence getPotion() {
		var sequence = new ActionSequence();
		sequence.add(new Take(charlotte, bluepotion ));
		return sequence;
	}
	
	private ActionSequence getDrinkPotion() {
		var sequence = new ActionSequence();
		sequence.add(new Drink(charlotte));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Poof));
		sequence.add(new Position(charlotte, city));
		sequence.add(new SetCameraFocus(charlotte));
		//create enemy character
		//sequence.add(new Character(enemy, Character.BodyType.A));
		
		sequence.add(new Attack(charlotte, enemy, true));
		sequence.add(new Die(enemy));
		sequence.add(new Take(charlotte, goldcup));
		sequence.add(new Dance(charlotte));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Aura));
		return sequence;
	}
	
	private ActionSequence getDanceWithPotion() {
		var sequence = new ActionSequence();
		sequence.add(new Dance(charlotte));
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
		var sequence = new ActionSequence();
		sequence.add(new Exit(charlotte, bedroom.getFurniture("Door"), true));
		sequence.add(new Position(charlotte, camp));
		sequence.add(new SetCameraFocus(charlotte));
		return sequence;
	}
	
	private ActionSequence getAttackNPC() {
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
		var sequence = new ActionSequence();
		sequence.add(new Position(charlotte, city));
		sequence.add(new SetCameraFocus(charlotte));
		sequence.add(new Attack(charlotte, enemy, false));
		sequence.add(new Attack(enemy, charlotte, true));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Death));
		sequence.add(new Die(charlotte));
		return sequence;
	}
	
	private ActionSequence getRefuseHelp() {
		var sequence = new ActionSequence();
		sequence.add(new CreateEffect(NPC, CreateEffect.Effect.Blackflame));
		sequence.add(new Attack(NPC, charlotte, true));
		sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Death));
		sequence.add(new Die(charlotte));
		return sequence;
	}
	
	private ActionSequence getPotionSequence() {
        var sequence = new ActionSequence();
        sequence.add(new Wave(charlotte));
        sequence.add(new Wave(witch));
        sequence.add(new SetNarration("Yay swordddd!"));
        sequence.add(new CreateEffect(witch, CreateEffect.Effect.Aura));
        return sequence;
    }
	
	private ActionSequence getEnenemydead() {
        var sequence = new ActionSequence();
        sequence.add(new SetCameraFocus(charlotte));
        sequence.add(new Take(charlotte, sword));
        sequence.add(new Attack(charlotte, enemy, true));
        sequence.add(new Dance(charlotte));
        sequence.add(new CreateEffect(enchantedBook, CreateEffect.Effect.Magic));
        return sequence;
    }

	private ActionSequence getNpcSequence() {
        var sequence = new ActionSequence();
        sequence.add(new Give(charlotte, bluepotion, villager));
        sequence.add(new SetNarration("A token of gratitude"));
        sequence.add(new CreateEffect(CreateEffect.Effect.Magic));
        return sequence;
    }
	
	private ActionSequence getTalkWithNpcSequence() {
        var sequence = new ActionSequence();
        sequence.add(new Dance(charlotte, villager));
        sequence.add(new CreateEffect(charlotte, CreateEffect.Effect.Happy));
        sequence.add(new CreateEffect(villager, CreateEffect.Effect.Happy));
        return sequence;
    }



	

}
