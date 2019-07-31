package com.runnzzerfitness.fragments.dialogs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class DialogFragment extends Fragment {

    @Nullable
    public abstract void setInitValue(double value);

    @NonNull
    public abstract void setDialogBuilder (DialogBuilder dialogBuilder);

}
