package ahn.mirim.firstiamnotme;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 안성현 on 2017-10-21.
 */

public class Recipe {
    String recipe[];

    public Recipe(){
        InputStream fi=GameThread.context.getResources().openRawResource(R.raw.recipe);

        try{
            byte[] data=new byte[fi.available()]; // 파일의 크기를 배열의 크기로
            fi.read(data);
            fi.close();

            String s=new String(data, "EUC-KR");
            String temp[]=s.split("\n");
            recipe=new String[temp.length];

            for(int i=0; i<temp.length; i++){
                recipe[i]=temp[i].trim();
                Log.v("알림", recipe[i]);
            }

        }catch (IOException e){}
    }

    public int check(String code){
        for(int i=0; i<recipe.length; i++)
            if(code.equals(recipe[i])) {
                Log.v("알림", recipe[i]);
                return i;
            }
        return -1;
    }


}
