.class final Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates$1;
.super Ljava/lang/Object;
.source "ItemSelectionSupport.java"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/os/Parcelable$Creator",
        "<",
        "Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .registers 1

    .prologue
    .line 414
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .registers 3

    .prologue
    .line 414
    invoke-virtual {p0, p1}, Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates$1;->createFromParcel(Landroid/os/Parcel;)Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates;

    move-result-object v0

    return-object v0
.end method

.method public createFromParcel(Landroid/os/Parcel;)Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates;
    .registers 4
    .param p1, "in"    # Landroid/os/Parcel;

    .prologue
    .line 417
    new-instance v0, Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1}, Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates;-><init>(Landroid/os/Parcel;Lorg/lucasr/twowayview/ItemSelectionSupport$1;)V

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .registers 3

    .prologue
    .line 414
    invoke-virtual {p0, p1}, Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates$1;->newArray(I)[Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates;

    move-result-object v0

    return-object v0
.end method

.method public newArray(I)[Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates;
    .registers 3
    .param p1, "size"    # I

    .prologue
    .line 422
    new-array v0, p1, [Lorg/lucasr/twowayview/ItemSelectionSupport$CheckedStates;

    return-object v0
.end method