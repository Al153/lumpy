#include <setjmp.h>

#ifndef GC_FILE
#define GC_FILE

#include "exception.h"

#ifndef NULL
#define NULL (void*)0
#endif

typedef struct gcnode { // Ein Node (= Objekt)
    void* data; //Das referenzierte Objekt
    char mark; //wurde das Objekt mariert?
    int size; //wie gro� das Objekt ist
    int typid; //welcher typ ist das objekt?
    void (*trace)(struct gcnode*); //Die Tracefunktion (f�hrt das markieren aus)
    struct gcnode* next; //Das n�chste Node
} GCNode;

typedef struct gcframe {
    GCNode*** refs; //Alle Referenzen
    int size; // wieviele referenzen
    struct gcframe* prev; //Voriges Frame
    ExceptionHolder* exception; //wohin gesprungen wird, bei einer exception
} GCFrame;

GCNode* currentNode; //Startnode
GCFrame* currentFrame;

void GCInit();
void GCDeInit();
void gccollect();
void stack_enter(int size, ExceptionHolder* jmp);
void stack_leave();
void stack_create(GCNode** node, int pos);
void standardTrace(GCNode* node);
GCNode* gc_malloc(int size, void(*tracefunc)(GCNode*));



#define TYP_INT 100
int exc_holder_int;
#define TYP_LONG 101
long exc_holder_long;
#define TYP_BYTE 102
char exc_holder_byte;
#define TYP_SHORT 103
short exc_holder_short;
#define TYP_FLOAT 104
float exc_holder_float;
#define TYP_DOUBLE 105
double exc_holder_double;
#define TYP_STRING 106
GCNode* exc_holder_obj;
#define TYP_BOOLEAN 108
char exc_holder_boolean;
#endif

