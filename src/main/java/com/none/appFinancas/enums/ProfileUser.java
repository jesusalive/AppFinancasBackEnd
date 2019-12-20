package com.none.appFinancas.enums;

public enum ProfileUser {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private int cod;
    private String role;

    private ProfileUser(int cod, String role) {
        this.cod = cod;
        this.role = role;
    }

    public int getCod() {
        return cod;
    }

    public String getRole() {
        return role;
    }

    public static ProfileUser toEnum(Integer cod) throws IllegalAccessException {

        if(cod == null){
            return null;
        }

        for(ProfileUser profile : ProfileUser.values()){
            if(cod.equals(profile.getCod())) {
                return profile;
            }
        }

        throw new IllegalAccessException("Numero de Profile inválido: " + cod);
    }
}
