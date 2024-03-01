import { Role } from "./enums/Role.enum";

export class Usuario {
    username: string = '';
    password: string = '';
    roles: Role[] = [];
}