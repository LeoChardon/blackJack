package com.leochardon.blackjack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.leochardon.blackjack.controller.BlackJack;
import com.leochardon.blackjack.model.Card;
import com.leochardon.blackjack.model.EmptyDeckException;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MAIN ACTIVITY LOG"; // pour debugger

    // variables vues servant dans les méthodes
    View viewDialog;
    Button noMoreButton;
    Button resetButton;
    Button anotherCardButton;
    Button miseButton;
    LinearLayout playerCards;
    LinearLayout bankCards;
    TextView playerScore;
    TextView bankScore;
    TextView deckCards;
    TextView playerName;
    TextView bankName;
    TextView mise;
    SeekBar mymisebar;
    TextView playerMoney;

    // variable de jeu
    BlackJack game;
    int money;
    int finalMoney = 0;
    int sabot;
    String playerNameString;

    // variable txt pour simplifier les concaténations de string
    String playerTxt;
    String bankTxt;
    String deviseTxt ;
    String YourmiseTxt;
    String cantZeroTxt ;
    String soYourMiseTxt ;
    String cantCreateTxt ;
    String deckCardsTxt ;
    String tripleMiseTxt ;
    String doubleMiseTxt ;
    String looseMiseTxt ;
    String scoreTxt;
    String scoreOtherTxt ;
    String playerJustDrawTxt;
    String bankJustDrawTxt ;
    String lookAtTheBottomTxt;
    String remiseTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dès qu'on lance l'application, on instancie les variable text et graphique

        // variables des textes
        bankTxt = getResources().getString(R.string.Banktxt);
        playerTxt = getResources().getString(R.string.Playertxt);
        deviseTxt = getResources().getString(R.string.moneyDevisetxt);
        YourmiseTxt = getResources().getString(R.string.yourMisetxt);
        cantZeroTxt = getResources().getString(R.string.cantMiseZerotxt);
        soYourMiseTxt = getResources().getString(R.string.soYourMisetxt);
        cantCreateTxt = getResources().getString(R.string.cantCreatetxt);
        deckCardsTxt = getResources().getString(R.string.deckCardstxt);
        tripleMiseTxt = getResources().getString(R.string.tripleMisetxt);
        doubleMiseTxt = getResources().getString(R.string.doubleMisetxt);
        looseMiseTxt = getResources().getString(R.string.looseMisetxt);
        scoreTxt = getResources().getString(R.string.scoretxt);
        scoreOtherTxt = getResources().getString(R.string.scoreOthertxt);
        playerJustDrawTxt = getResources().getString(R.string.playerJustDrawtxt);
        bankJustDrawTxt = getResources().getString(R.string.bankJustDrawtxt);
        lookAtTheBottomTxt = getResources().getString(R.string.lookAtTheBottomtxt);
        remiseTxt = getResources().getString(R.string.remisetxt);

        // si on lancer la console pour vérifier ou debugger
        new BlackJackConsole();


        // trouver et instancier toutes les vues
        bankName = findViewById(R.id.bankName);
        playerName = findViewById(R.id.playerName);
        miseButton = findViewById(R.id.miseButton);
        noMoreButton = findViewById(R.id.noMoreButton);
        anotherCardButton = findViewById(R.id.anotherCardButton);
        resetButton = findViewById(R.id.resetButton);
        playerCards = findViewById(R.id.playerCardPanel);
        bankCards = findViewById(R.id.bankCardPanel);
        playerScore = findViewById(R.id.playerScore);
        bankScore = findViewById(R.id.bankScore);
        deckCards = findViewById(R.id.deckCards);
        playerMoney = findViewById(R.id.playerMoney);
        mise = findViewById(R.id.mise);
        mymisebar = findViewById(R.id.mymisebar);

        // instancie les variable de jeu
        playerNameString = playerTxt;
        money = 5000;
        sabot = 3;

        // Remplir les vue avec les valeur par default
        initBeginOfGame();

        mymisebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            // Fonction appele a chaque changement de la bar.
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                int MoneyTemp = money;
                progress = progressValue;
                mise.setText(YourmiseTxt + mymisebar.getProgress() + "/" + mymisebar.getMax()+deviseTxt);
                MoneyTemp= (money-progress);
                playerMoney.setText(""+MoneyTemp + deviseTxt);
                finalMoney = MoneyTemp;

            }

            // Notification toast quand l'utilisateur touche la bar
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Voici votre mise", Toast.LENGTH_SHORT).show();
            }

            // Notification toast quand l'utilisateur leve son doight
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mise.setText(YourmiseTxt + mymisebar.getProgress() + "/" + mymisebar.getMax()+deviseTxt);
                if(progress==0){
                    Toast.makeText(getApplicationContext(), cantZeroTxt, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), soYourMiseTxt+progress+deviseTxt, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void initBeginOfGame(){
        // Remplir les vue avec les valeur par default
        resetButton.setEnabled(false);
        anotherCardButton.setEnabled(false);
        noMoreButton.setEnabled(false);
        miseButton.setEnabled(true);
        mymisebar.setEnabled(true);
        bankName.setText("");
        playerName.setText(playerNameString);
        playerScore.setText("");
        bankScore.setText("");
        deckCards.setText("");
        playerMoney.setText(""+money + deviseTxt);
        mymisebar.setMax(300);
        mymisebar.setProgress(0);
        mise.setText(YourmiseTxt + mymisebar.getProgress() + "/" + mymisebar.getMax()+deviseTxt);


        // affiche la page de présentation du jeu
        addToPanel(bankCards,"blackjack");
        addToPanel(playerCards,"card_blackjack");
        addToPanel(playerCards,"card_blackjack");
        addToPanel(playerCards,"card_blackjack");
        addToPanel(playerCards,"card_blackjack");
        addToPanel(playerCards,"card_blackjack");
        addToPanel(playerCards,"card_blackjack");
    }

    public void launchGame(View v){
        /*
        Fonction permettant de lancer et créer le jeu
        En cas de partie existante, elle met à jour l'argent du joueur et les layouts
         */

        if(mymisebar.getProgress()==0){
            Toast.makeText(getApplicationContext(), cantZeroTxt, Toast.LENGTH_SHORT).show();
        }
        else{
            resetButton.setEnabled(true);
            anotherCardButton.setEnabled(true);
            noMoreButton.setEnabled(true);
            if(game == null) {
                try {
                    game = new BlackJack(sabot);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), cantCreateTxt, Toast.LENGTH_SHORT).show();
                }
            }
            money = finalMoney ;
            updateAllLayout();
            bankName.setText(bankTxt);
            playerName.setText(playerNameString);
            mise.setText(soYourMiseTxt+ mymisebar.getProgress() + "/" + mymisebar.getMax()+deviseTxt);
            mymisebar.setEnabled(false);
            miseButton.setEnabled(false);
            resetButton.setEnabled(false);
        }
    }


    private void updateAllLayout(){
        /*
        Fonction permettant de mettre à joueur les layout
        Elle verifie egalement si il a un vainqueur
         */
        bankName.setText(bankTxt);
        playerName.setText(playerTxt);
        playerCards.removeAllViews();
        bankCards.removeAllViews();
        updateBankPanel();
        updatePlayerPanel();
        whoWin();
        updateDeckCardsLay();
    }

    private void clearLayout(){
        /*
        Fonction permettant de clear tout les layouts
         */
        playerCards.removeAllViews();
        bankCards.removeAllViews();
        bankScore.setText("");
        playerScore.setText("");
        deckCards.setText("");
        bankName.setText("");
        playerName.setText("");
    }

    private void updateDeckCardsLay(){
        /*
        Fonction qui met a jour le nombre de carte du deck sur l'écran
         */
        String deck = deckCardsTxt+game.getDeckLength();
        deckCards.setText(deck);
    }

    private void whoWin(){
        /*
        Fonction verifiant qui a gagné
        Dans cette fonction le resultat de la mise et du gain est aussi mis à jour
         */
        if(game.isGameFinished()){
            if(game.isPlayerWinner()){
                addToPanel(playerCards,"card_winner");
                if(game.getPlayerBest()==21){
                    addToPanel(playerCards,"card_blackjack");
                    mymisebar.setProgress(mymisebar.getProgress()*3);
                    mise.setText(tripleMiseTxt+ "\uD83D\uDCB5\uD83D\uDCB5\uD83D\uDCB5: "+mymisebar.getProgress());
                    money += mymisebar.getProgress()*3;
                    playerMoney.setText(""+money+deviseTxt);
                }else{
                    mymisebar.setProgress(mymisebar.getProgress()*2);
                    mise.setText(doubleMiseTxt+ "\uD83D\uDE01: "+mymisebar.getProgress());
                    money += mymisebar.getProgress()*2;
                    playerMoney.setText(""+money+"$");
                }

            }else{
                addToPanel(playerCards,"card_looser");
                int temp = mymisebar.getProgress();
                mymisebar.setProgress(0);
                mise.setText(looseMiseTxt+"\uD83D\uDE22: "+temp);

            }
            if(game.isBankWinner()){
                addToPanel(bankCards,"card_winner");
                if(game.getBankBest()==21){
                    addToPanel(bankCards,"card_blackjack");
                }
            }else{
                addToPanel(bankCards,"card_looser");
            }

            anotherCardButton.setEnabled(false);
            noMoreButton.setEnabled(false);
            resetButton.setEnabled(true);

        }
    }

    private void updatePlayerPanel(){
        /*
        Fonction qui met à jour les infos du joueur
         */
        ArrayList<Card> cards = game.getPlayerCardList();
        for(Card c: cards){
            String name = c.getCardFullName();
            addToPanel(playerCards,name);
        }
        playerScore.setText(scoreTxt+game.getPlayerBest());
    }

    private void updateBankPanel(){
        /*
        Fonction qui met à jour les infos de la banque
         */
        ArrayList<Card> cards = game.getBankCardList();
        for(Card c: cards){
            String name = c.getCardFullName();
            addToPanel(bankCards,name);
        }
        bankScore.setText(scoreOtherTxt+game.getBankBest());
    }

    public void playerDrawAnotherCard(View v){
        /*
        Fonction qui fait jouer le joueur, lorsqu'il appuie sur un bouton
        cette fonction appelle ensuite le rafraichissemen des layouts

        Si il n'y a plus de carte dans le deck, une exception est leve et un dialog apparait
         */
        try{
            game.playerDrawAnotherCard();
            Toast.makeText(getApplicationContext(), playerJustDrawTxt, Toast.LENGTH_SHORT).show();
            updateAllLayout();
        }catch (EmptyDeckException e){
            noMoreCard(getApplicationContext());
        }

    }

    public void bankLastTurn(View v){
        /*
        Fonction qui fait jouer la banque, lorsque le joueur appuie sur un bouton
        cette fonction appelle ensuite le rafraichissemen des layouts

        Si il n'y a plus de carte dans le deck, une exception est leve et un dialog apparait
         */
        try{
            game.bankLastTurn();
            Toast.makeText(getApplicationContext(), bankJustDrawTxt, Toast.LENGTH_SHORT).show();
            updateAllLayout();
        }catch (EmptyDeckException e){
            noMoreCard(getApplicationContext());
        }

    }

    private void newMatch(){
        /*
        Fonction permettant au joueur de remiser et rejouer après la fin d'une partie
         */
        resetButton.setEnabled(false);
        miseButton.setEnabled(true);
        mymisebar.setEnabled(true);
        clearLayout();
        playerName.setText(lookAtTheBottomTxt+"\uD83D\uDE09 \uD83D\uDCB5");
        finalMoney = money;
        finalMoney -= mymisebar.getProgress();
        playerMoney.setText(""+finalMoney+"$");
        mise.setText(remiseTxt+mymisebar.getProgress());
    }

    public void reset(View v){
        /*
        Fonction qui reset la partie, lorsque le joueur appuie sur un bouton
        cette fonction appelle ensuite le rafraichissemen des layouts
        Le bouton reset n'est disponible que si la partie est terminé

        Si il n'y a plus de carte dans le deck, une exception est leve et un dialog apparait
         */
        try{
            game.reset();
            newMatch();
        }catch (EmptyDeckException e){
            noMoreCard(getApplicationContext());
            Log.e(TAG,e.toString());
        }
    }


    public void restart(){
        /*
        Fonction qui est appelé lorsque le joueur a confirmer le changement
        de nom, de nombre de deck ou d'argent
         */
        clearLayout();
        initBeginOfGame();
        game = null;

    }

    public void addToPanel(LinearLayout lay, String token){
        ImageView imv = new ImageView(this);
        int resID = getResources().getIdentifier(token, "drawable",getPackageName());
        Bitmap tempBMP = BitmapFactory.decodeResource(getResources(),resID);
        imv.setPadding(10,10,10,10);
        imv.setImageBitmap(tempBMP);
        lay.addView(imv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.menu_item) {
            myDialog(getApplicationContext());
        }
        return true;
    }

    public boolean myDialog(Context context){
        LayoutInflater inflater = getLayoutInflater();
        View dialog = inflater.inflate(R.layout.diag_config,null);
        final RadioButton blackChoice = dialog.findViewById(R.id.radioButtonBlack);
        final RadioButton greenChoice = dialog.findViewById(R.id.radioButtonGreen);
        final RadioButton langageFR = dialog.findViewById(R.id.radioButtonFR);
        final RadioButton langageEN = dialog.findViewById(R.id.radioButtonENG);
        final EditText numberDecks = dialog.findViewById(R.id.numberOfDeck);
        final EditText initMoney = dialog.findViewById(R.id.initMoney);
        final EditText playerNameEdit = dialog.findViewById(R.id.playerNameEdit);
        final ConstraintLayout bg = findViewById(R.id.bigConstraintID);
        playerNameEdit.setText(playerNameString);
        numberDecks.setText(""+sabot);
        initMoney.setText(""+money);

        AlertDialog.Builder dial = new AlertDialog.Builder(this);
        dial.setTitle(R.string.configBlackJacktxt);
        dial.setView(dialog);
        dial.setPositiveButton(R.string.confirmertxt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean haveToRestart = false;
                if(blackChoice.isChecked()){
                    bg.setBackgroundColor(Color.DKGRAY);
                }if(greenChoice.isChecked()){
                    bg.setBackgroundColor(getColor(R.color.green));
                }
                int newMoney = parseInt(initMoney.getText().toString());
                if(money != newMoney){

                    money = newMoney;
                    haveToRestart = true;
                }
                int newSabot = parseInt(numberDecks.getText().toString());
                if(sabot != newSabot){

                    sabot = newSabot;
                    haveToRestart = true;
                }
                if(!playerNameString.equals(playerNameEdit.getText().toString())){
                    playerNameString = playerNameEdit.getText().toString();

                    haveToRestart = true;
                }
                if(langageFR.isChecked()){
                    LocaleHelper.setLocale(MainActivity.this, "fr");
                    recreate();
                }if(langageEN.isChecked()){
                    LocaleHelper.setLocale(MainActivity.this, "en");
                    recreate();
                }
                if(haveToRestart){
                    restart();
                }
                Toast.makeText(getApplicationContext(), R.string.actionConfirmtxt, Toast.LENGTH_SHORT).show();
            }
        });

        dial.setNegativeButton(R.string.annulertxt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getApplicationContext(), R.string.actionCanceltxt, Toast.LENGTH_SHORT).show();
            }
        });

        dial.show();
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //outState.put;

    }

    @Override
    protected  void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    public boolean noMoreCard(Context context){

        AlertDialog.Builder dial = new AlertDialog.Builder(this);
        dial.setTitle(R.string.noMoreCard);
        dial.setMessage(R.string.messageNoMore);
        dial.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dial.show();
        return true;
    }


}
