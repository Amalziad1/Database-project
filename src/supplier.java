 
public class supplier {
		
        private String supplier_name;
        private int phone ;
        private String address;
        private String email;
        static supplier prov;
        public supplier (String supplier_name, int phone, String address,String email) {
            super();
            
            this.supplier_name = supplier_name;
            this.phone = phone;
            this.address = address;
             this.email = email;
        }
 public supplier() {
	 
 }

	public String getSupplier_name() {
			return supplier_name;
		}


public String getAddress() {
        return address;
    }
	

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

  

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
	
	
}

