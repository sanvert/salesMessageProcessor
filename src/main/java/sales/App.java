package sales;

import sales.adapter.AdapterMappings;
import sales.adapter.MessageAdapter;
import sales.analytics.AnalyticsResult;
import sales.analytics.MessageAnalytics;
import sales.processor.MessageProcessor;
import sales.processor.ProcessorMappings;
import sales.type.NotificationType;

import java.util.Scanner;

/**
 * Message Processor App
 *
 */
public class App 
{
    private static volatile boolean inProcess = true;

    public static void main( String[] args )
    {
        System.out.println( "Sales Notification Manager" );
        Scanner sc = new Scanner(System.in);

        while(inProcess) {
            System.out.println("Input:");
            String line = sc.nextLine().trim();
            if(line.equalsIgnoreCase("exit"))
                inProcess=false;
            else{
                NotificationType messageType = MessageUtils.getSalesMessageNotificationType(line);
                if(messageType == NotificationType.INVALID_MSG)
                    System.out.println(MessageUtils.INVALID_MSG);
                else {
                    MessageAdapter adapter = AdapterMappings.get(messageType);
                    MessageProcessor processor = ProcessorMappings.get(messageType);
                    String output = processor.process(adapter.adapt(line.substring(1)));
                    System.out.println(output);
                    AnalyticsResult result = MessageAnalytics.getInstance().generate();
                    System.out.println(result.getReport());
                }
            }
        }
    }
}
