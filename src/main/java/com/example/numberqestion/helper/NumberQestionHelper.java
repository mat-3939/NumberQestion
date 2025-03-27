package com.example.numberqestion.helper;

import com.example.numberqestion.entity.qestion;
import com.example.numberqestion.form.NumberQestionForm;

/*NumberQestionHelperクラス*/
public class NumberQestionHelper {
    /*NumberQestionFormをqestionに変換するメソッド*/
    public static qestion convertToqestion(NumberQestionForm form) {
        qestion qestion = new qestion();
        qestion.setId(form.getId());
        qestion.setPlayDate(form.getPlayDate());
        qestion.setPlayName(form.getPlayName());
        qestion.setClearCount(form.getClearCount());
        return qestion;
    }

    /*qestionをNumberQestionFormに変換するメソッド*/
    public static NumberQestionForm convertToNumberQestionForm(qestion qestion) {
        NumberQestionForm form = new NumberQestionForm();
        form.setId(qestion.getId());
        form.setPlayDate(qestion.getPlayDate());
        form.setPlayName(qestion.getPlayName());
        form.setClearCount(qestion.getClearCount());
        return form;
    }
}
