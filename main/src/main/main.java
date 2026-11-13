package main;

public class main {
	
	
	//Simple testing
	
	public static void main(String [] args) {
		
		slidingWindow user = new slidingWindow();
		var count = 0;

		
		for(var i = 0; i <= 30; i++){
			try {
				int randomNum = (int)((Math.random() * 9)+1);
//				int randomNum = 1;
				count++;
				IO.println("running request " + count + " random number is " + randomNum);
			
					var result = user.allowRequest("ID1");
					if(result == true) {
						IO.println("True");
						IO.println("-------------------------");
					}
					if(result == false) {
						IO.println(user.retryAfterMs("ID1"));
						IO.println("False");
						IO.println("-------------------------");
					}
					
					if (Math.random() >= 1) {
						Thread.sleep((randomNum*1000));
					}
					else {
						Thread.sleep((randomNum*100));
					}
					
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

}
}
