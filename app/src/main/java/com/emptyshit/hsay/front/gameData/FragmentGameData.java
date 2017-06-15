package com.emptyshit.hsay.front.gameData;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emptyshit.hsay.R;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.dataTypes.TimeType;
import com.emptyshit.hsay.timeMeasureComponent.TimeMeasureComponentInterface;

public class FragmentGameData extends Fragment {

    private TextView gameDataBestTimeTextView,gameDataAvgTimeTextView;
    private TextView gameDataLast1Of5TextView, gameDataLast2Of5TextView;
    private TextView gameDataLast3Of5TextView, gameDataLast4Of5TextView, gameDataLast5Of5TextView;

    private TimeMeasureComponentInterface timeMeasureComponentInterface = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentGameData = inflater.inflate(R.layout.fragment_game_data, container, false);

        this.timeMeasureComponentInterface = App.getTimeMeasureComponentInterface();

        this.gameDataBestTimeTextView = (TextView) fragmentGameData.findViewById(R.id.gameDataBestTimeTextView);
        this.gameDataAvgTimeTextView = (TextView) fragmentGameData.findViewById(R.id.gameDataBestTimeTextView);
        this.gameDataLast1Of5TextView = (TextView) fragmentGameData.findViewById(R.id.gameDataLast1Of5TextView);
        this.gameDataLast2Of5TextView = (TextView) fragmentGameData.findViewById(R.id.gameDataLast2Of5TextView);
        this.gameDataLast3Of5TextView = (TextView) fragmentGameData.findViewById(R.id.gameDataLast3Of5TextView);
        this.gameDataLast4Of5TextView = (TextView) fragmentGameData.findViewById(R.id.gameDataLast4Of5TextView);
        this.gameDataLast5Of5TextView = (TextView) fragmentGameData.findViewById(R.id.gameDataLast5Of5TextView);

        this.gameDataBestTimeTextView.setText("");
        this.gameDataAvgTimeTextView.setText("");
        this.gameDataLast1Of5TextView.setText("");
        this.gameDataLast2Of5TextView.setText("");
        this.gameDataLast3Of5TextView.setText("");
        this.gameDataLast4Of5TextView.setText("");
        this.gameDataLast5Of5TextView.setText("");

        if(this.timeMeasureComponentInterface.getTimesPlayed() > 0) {
            this.gameDataBestTimeTextView.setText(this.timeMeasureComponentInterface.getMyBestTimeOfGame().toString());
            this.gameDataAvgTimeTextView.setText(this.timeMeasureComponentInterface.getMyAvgTimeOfGame().toString());
            TimeType[] timeDatas = this.timeMeasureComponentInterface.getAllTimeOfGame();
            for(int i = 0; i < 5 && i < timeDatas.length; i++){
                switch(i){
                    case 0:{
                        this.gameDataLast1Of5TextView.setText(timeDatas[i].toString());
                        continue;
                    }
                    case 1:{
                        this.gameDataLast2Of5TextView.setText(timeDatas[i].toString());
                        continue;
                    }
                    case 2:{
                        this.gameDataLast3Of5TextView.setText(timeDatas[i].toString());
                        continue;
                    }
                    case 3:{
                        this.gameDataLast4Of5TextView.setText(timeDatas[i].toString());
                        continue;
                    }
                    case 4: {
                        this.gameDataLast5Of5TextView.setText(timeDatas[i].toString());
                        break;
                    }
                }
            }
        }
        return fragmentGameData;
    }
}
