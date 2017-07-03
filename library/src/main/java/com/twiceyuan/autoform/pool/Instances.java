package com.twiceyuan.autoform.pool;

import com.twiceyuan.autoform.provider.FormItemProvider;
import com.twiceyuan.autoform.provider.FormItemValidator;
import com.twiceyuan.autoform.provider.HintProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by twiceYuan on 2017/7/2.
 * <p>
 * 全局单例池
 */
public class Instances {

    private static Map<Class<? extends FormItemProvider>, FormItemProvider>   mFormItemProviderMap  = new HashMap<>();
    private static Map<Class<? extends HintProvider>, HintProvider>           mHintProviderMap      = new HashMap<>();
    private static Map<Class<? extends FormItemValidator>, FormItemValidator> mFormItemValidatorMap = new HashMap<>();

    public static <T> T newInstance(Class<T> instanceClass) {
        try {
            return instanceClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends FormItemProvider> T getFormItemProvider(Class<T> providerClass) {
        FormItemProvider provider = mFormItemProviderMap.get(providerClass);
        if (provider == null) {
            try {
                provider = providerClass.newInstance();
                mFormItemProviderMap.put(providerClass, provider);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //noinspection unchecked
        return (T) provider;
    }

    public static <T extends FormItemValidator> T getFormItemValidator(Class<T> cls) {
        FormItemValidator validator = mFormItemValidatorMap.get(cls);
        if (validator == null) {
            try {
                validator = cls.newInstance();
                mFormItemValidatorMap.put(cls, validator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //noinspection unchecked
        return (T) validator;
    }


    public static <T extends HintProvider> T getHintProvider(Class<T> providerClass) {
        HintProvider provider = mHintProviderMap.get(providerClass);
        if (provider == null) {
            try {
                provider = providerClass.newInstance();
                mHintProviderMap.put(providerClass, provider);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //noinspection unchecked
        return (T) provider;
    }
}