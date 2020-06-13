import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { AcheteurVendeurComponent } from './acheteur-vendeur.component';
import { AcheteurVendeurDetailComponent } from './acheteur-vendeur-detail.component';
import { AcheteurVendeurUpdateComponent } from './acheteur-vendeur-update.component';
import { AcheteurVendeurDeleteDialogComponent } from './acheteur-vendeur-delete-dialog.component';
import { acheteurVendeurRoute } from './acheteur-vendeur.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(acheteurVendeurRoute)],
  declarations: [
    AcheteurVendeurComponent,
    AcheteurVendeurDetailComponent,
    AcheteurVendeurUpdateComponent,
    AcheteurVendeurDeleteDialogComponent,
  ],
  entryComponents: [AcheteurVendeurDeleteDialogComponent],
})
export class EtatregAcheteurVendeurModule {}
