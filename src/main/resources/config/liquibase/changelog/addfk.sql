ALTER TABLE "public"."jobs" ADD CONSTRAINT "fk_user_createdby" FOREIGN KEY ("created_by") REFERENCES "public"."jhi_user" ("login") ON UPDATE CASCADE ON DELETE SET NULL,
ADD CONSTRAINT "fk_user_modifiedby" FOREIGN KEY ("last_modified_by") REFERENCES "public"."jhi_user" ("login") ON UPDATE CASCADE ON DELETE SET NULL;
