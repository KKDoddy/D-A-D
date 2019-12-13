package domain.bean;

import java.io.Serializable;

public class Option implements Serializable{
        private String id;
        private String name;
        private double price;

        public Option(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        
        

        public void setPrice(double price) {
            this.price = price;
        }
}