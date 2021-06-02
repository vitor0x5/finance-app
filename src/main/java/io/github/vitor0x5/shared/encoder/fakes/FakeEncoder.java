package io.github.vitor0x5.shared.encoder.fakes;

import io.github.vitor0x5.shared.encoder.Encoder;

public class FakeEncoder implements Encoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.equals(s);
    }
}
