#!/bin/sh

PORT="9093"
FILE="ms-servicio-tickets.jar"
CWD=$(cd `dirname $0` && pwd)
CONF="$CWD/conf"
LOG="ms-tickets-$PORT.log"
JMX="-Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=5$PORT -Dcom.sun.management.jmxremote.ssl=false"

PIDDIR="."
REALDIR=`pwd`
ME=`basename "$0"|sed "s/\.sh$//g"`

PIDFILE="$ME.pid"


APP_NAME=`echo "$ME"|sed "s/-[0-9]\{4\}$//g"`

PSEXE="/usr/bin/ps"
if [ ! -x "$PSEXE" ]
then
    PSEXE="/bin/ps"
    if [ ! -x "$PSEXE" ]
    then
        echo "Unable to locate 'ps'."
        echo "Please report this message along with the location of the command on your system."
        exit 1
    fi
fi


if [ "X$JAVA_HOME" = "X" ]
    then
       echo "JAVA_HOME variable is not set,  set to /opt/j2sdk"
       export JAVA_HOME=/opt/j2sdk   
fi

COMMAND_LINE="$JAVA_HOME/bin/java -cp $FILE:$CONF $JMX -Dloader.main=ar.gob.hcdn.ticket.ServicioTicketRun org.springframework.boot.loader.PropertiesLauncher --server.port=$PORT"


getpid() {
    if [ -f "$PIDFILE" ]
    then
        if [ -r "$PIDFILE" ]
        then
            pid=`cat "$PIDFILE"`
            if [ "X$pid" != "X" ]
            then
                # It is possible that 'a' process with the pid exists but that it is not the
                #  correct process.  This can happen in a number of cases, but the most
                #  common is during system startup after an unclean shutdown.
                pidtest=`$PSEXE -p $pid -o args | grep "$APP_NAME" | tail -1`
                if [ "X$pidtest" = "X" ]
                then
                    # This is a stale pid file.
                    rm -f "$PIDFILE"
                    echo "Removed stale pid file: $PIDFILE"
                    pid=""
                fi
            fi
        else
            echo "Cannot read $PIDFILE."
            exit 1
        fi
    fi
}

testpid() {
    pid=`$PSEXE -p $pid | grep $pid | grep -v grep | awk '{print $1}' | tail -1`
    if [ "X$pid" = "X" ]
    then
        # Process is gone so remove the pid file.
        rm -f "$PIDFILE"
        pid=""
    fi
}

status() {
    getpid
    if [ "X$pid" = "X" ]
    then
        echo "$APP_LONG_NAME is not running."
        exit 1
    else
        echo "$APP_LONG_NAME is running ($pid)."
        exit 0
    fi
}

start() {
    echo "Starting $APP_LONG_NAME..."
    getpid
    if [ "X$pid" = "X" ]
    then
        # The string passed to eval must handles spaces in paths correctly.
       nohup $COMMAND_LINE > $LOG 2>&1 &
       echo $! >>$PIDFILE 
       exit 0
    else
        echo "$APP_NAME is already running."
        exit 1
    fi
    getpid
    if [ "X$pid" != "X" ]
    then
        echo "Started $APP_NAME."
    else
        echo "Failed to start $APP_NAME."
    fi    
}

stop() {
    echo "Stopping $APP_LONG_NAME..."
    getpid
    if [ "X$pid" = "X" ]
    then
        echo "$APP_NAME was not running."
    else
            # Running so try to stop it.
            kill $pid
            if [ $? -ne 0 ]
            then
                # An explanation for the failure should have been given
                echo "Unable to stop $APP_LONG_NAME."
                exit 1
            fi
       
        # We can not predict how long it will take for the wrapper to
        #  actually stop as it depends on settings in wrapper.conf.
        #  Loop until it does.
        savepid=$pid
        CNT=0
        TOTCNT=0
        while [ "X$pid" != "X" ]
        do
            # Show a waiting message every 5 seconds.
            if [ "$CNT" -lt "5" ]
            then
                CNT=`expr $CNT + 1`
            else
                echo "Waiting for $APP_LONG_NAME to exit..."
                CNT=0
            fi
            TOTCNT=`expr $TOTCNT + 1`

            sleep 1

            testpid
        done

        pid=$savepid
        testpid
        if [ "X$pid" != "X" ]
        then
            echo "Failed to stop $APP_NAME."
            exit 1
        else
            echo "Stopped $APP_NAME."
        fi
    fi
}

case "$1" in
        start) start
            ;;
        stop) stop
            ;;
	status) status
            ;;
        --*) echo "bad option $1"
            ;;
        *) echo "argument missing"
            ;;
    esac


