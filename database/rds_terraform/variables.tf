variable "private_subnets" {
  description = "Private subnets"
  type = "list"
  default = ["subnet-05f5e8b2da62e037c","subnet-0d66c441b99ce3258"]
}

variable "public_subnet_cidr" {
  description = "Public subnets"
  type = "list"
  default = ["172.31.0.0/20","172.31.32.0/20","172.31.16.0/20"]
}