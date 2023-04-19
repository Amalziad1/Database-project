
public class CustOrder {
	public int C_ID;
	public int O_ID;
	public String C_name;
	public String C_Address;
	public String O_Status;
	public double Sum;

	public CustOrder(int c_ID, int o_ID, String c_name, String c_Address, String o_Status) {
		super();
		C_ID = c_ID;
		O_ID = o_ID;
		C_name = c_name;
		C_Address = c_Address;
		O_Status = o_Status;
	}

	public CustOrder(int c_ID, String c_name, String c_Address, double sum) {
		super();
		C_ID = c_ID;
		C_name = c_name;
		C_Address = c_Address;
		Sum = sum;
	}

	public int getC_ID() {
		return C_ID;
	}

	public void setC_ID(int c_ID) {
		C_ID = c_ID;
	}

	public int getO_ID() {
		return O_ID;
	}

	public void setO_ID(int o_ID) {
		O_ID = o_ID;
	}

	public String getC_name() {
		return C_name;
	}

	public void setC_name(String c_name) {
		C_name = c_name;
	}

	public String getC_Address() {
		return C_Address;
	}

	public void setC_Address(String c_Address) {
		C_Address = c_Address;
	}

	public String getO_Status() {
		return O_Status;
	}

	public void setO_Status(String o_Status) {
		O_Status = o_Status;
	}

	public double getSum() {
		return Sum;
	}

	public void setSum(double sum) {
		Sum = sum;
	}

	
	
}
