JAVAC=javac
SOURCES = $(wildcard *.java)
CLASSES = $(SOURCES:.java=.class)
MAIN = Ring
JVM = java

all: $(CLASSES)

clear :
	rm -f *.class *~

%.class : %.java
	$(JAVAC) $<

exec: $(MAIN).class
	$(JVM) $(MAIN)
