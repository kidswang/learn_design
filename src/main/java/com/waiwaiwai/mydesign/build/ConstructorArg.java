package com.waiwaiwai.mydesign.build;

import lombok.ToString;
import org.junit.Test;

import java.util.Objects;

/**
 * 在下面的 ConstructorArg 类中，
 * 当 isRef 为 true 的时候，arg 表示 String 类型的 refBeanId，type 不需要设置；
 * 当 isRef 为 false 的时候，arg、type 都需要设置。
 * 请根据这个需求，完善 ConstructorArg 类。
 */
@ToString
public class ConstructorArg {
    private boolean isRef;
    private Class type;
    private Object arg;

    private ConstructorArg(Builder builder) {
        this.isRef = builder.isRef;
        this.type = builder.type;
        this.arg = builder.arg;
    }

    public static class Builder {
        private boolean isRef;
        private Class<?> type;
        private Object arg;

        public ConstructorArg build() {
            if (Objects.isNull(this.arg)) {
                throw new RuntimeException("arg can not null");
            }
            if (isRef) {
                if (!(arg instanceof String) && Objects.isNull(type)) {
                    throw new RuntimeException("type can not null");
                }
            } else {
                if (Objects.isNull(type)) {
                    throw new RuntimeException("type can not null");
                }
            }
            return new ConstructorArg(this);
        }

        public Builder setIsRef(boolean isRef) {
            this.isRef = isRef;
            return this;
        }

        public Builder setType(Class<?> type) {
            this.type = type;
            return this;
        }

        public Builder setArg(Object arg) {
            this.arg = arg;
            return this;
        }
    }
}
