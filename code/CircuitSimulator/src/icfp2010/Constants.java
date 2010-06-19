package icfp2010;
import icfp2010.gateFunctions.TritFunction;
import icfp2010.gateFunctions.TritMultAddTwoFunction;
import icfp2010.gateFunctions.TritSubFunction;

import java.util.List;


public class Constants {

	/* A - B */
	public static final int[][] LEFT_FUNCTION_TABLE = new int[][]
        {
		    {0,2,1},
		    {1,0,2},
		    {2,1,0}
        };
	
	/* A * B + 2 */
	public static final int[][] RIGHT_FUNCTION_TABLE = new int[][]
        {
		    {2,2,2},
		    {2,0,1},
		    {2,1,0}
        };
	
	public static final TritFunction LEFT_FUNCTION = new TritSubFunction();
	public static final TritFunction RIGHT_FUNCTION = new TritMultAddTwoFunction();
	
	public static final List<Trit> MASTER_INPUT = TritUtils.tritsFromString("01202101210201202");
}
