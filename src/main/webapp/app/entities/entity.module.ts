import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'bank-infos',
        loadChildren: () => import('./bank-infos/bank-infos.module').then(m => m.EtatregBankInfosModule),
      },
      {
        path: 'files-infos',
        loadChildren: () => import('./files-infos/files-infos.module').then(m => m.EtatregFilesInfosModule),
      },
      {
        path: 'acheteur-vendeur',
        loadChildren: () => import('./acheteur-vendeur/acheteur-vendeur.module').then(m => m.EtatregAcheteurVendeurModule),
      },
      {
        path: 'nature-debiteur',
        loadChildren: () => import('./nature-debiteur/nature-debiteur.module').then(m => m.EtatregNatureDebiteurModule),
      },
      {
        path: 'nature-depot',
        loadChildren: () => import('./nature-depot/nature-depot.module').then(m => m.EtatregNatureDepotModule),
      },
      {
        path: 'objet-credit',
        loadChildren: () => import('./objet-credit/objet-credit.module').then(m => m.EtatregObjetCreditModule),
      },
      {
        path: 'secteur-activite',
        loadChildren: () => import('./secteur-activite/secteur-activite.module').then(m => m.EtatregSecteurActiviteModule),
      },
      {
        path: 'bp-2-infos',
        loadChildren: () => import('./bp-2-infos/bp-2-infos.module').then(m => m.EtatregBp2InfosModule),
      },
      {
        path: 'bp-3-infos',
        loadChildren: () => import('./bp-3-infos/bp-3-infos.module').then(m => m.EtatregBp3InfosModule),
      },
      {
        path: 'bp-4-infos',
        loadChildren: () => import('./bp-4-infos/bp-4-infos.module').then(m => m.EtatregBp4InfosModule),
      },
      {
        path: 'bdu-cdb',
        loadChildren: () => import('./bdu-cdb/bdu-cdb.module').then(m => m.EtatregBduCdbModule),
      },
      {
        path: 'bdu-depot',
        loadChildren: () => import('./bdu-depot/bdu-depot.module').then(m => m.EtatregBduDepotModule),
      },
      {
        path: 'bdu-effet',
        loadChildren: () => import('./bdu-effet/bdu-effet.module').then(m => m.EtatregBduEffetModule),
      },
      {
        path: 'bdu-autor',
        loadChildren: () => import('./bdu-autor/bdu-autor.module').then(m => m.EtatregBduAutorModule),
      },
      {
        path: 'chargement',
        loadChildren: () => import('./chargement/chargement.module').then(m => m.EtatregChargementModule),
      },
      {
        path: 'dechargement',
        loadChildren: () => import('./dechargement/dechargement.module').then(m => m.EtatregDechargementModule),
      },
      {
        path: 'periode',
        loadChildren: () => import('./periode/periode.module').then(m => m.EtatregPeriodeModule),
      },
      {
        path: 'charge-file',
        loadChildren: () => import('./charge-file/charge-file.module').then(m => m.EtatregChargeFileModule),
      },
      {
        path: 'bp-1-com',
        loadChildren: () => import('./bp-1-com/bp-1-com.module').then(m => m.EtatregBp1ComModule),
      },
      {
        path: 'bp-1-his',
        loadChildren: () => import('./bp-1-his/bp-1-his.module').then(m => m.EtatregBp1HisModule),
      },
      {
        path: 'bp-2-com',
        loadChildren: () => import('./bp-2-com/bp-2-com.module').then(m => m.EtatregBp2ComModule),
      },
      {
        path: 'bp-3-his',
        loadChildren: () => import('./bp-3-his/bp-3-his.module').then(m => m.EtatregBp3HisModule),
      },
      {
        path: 'crp-atr',
        loadChildren: () => import('./crp-atr/crp-atr.module').then(m => m.EtatregCrpAtrModule),
      },
      {
        path: 'bpdevise',
        loadChildren: () => import('./bpdevise/bpdevise.module').then(m => m.EtatregBpdeviseModule),
      },
      {
        path: 'bpbqe',
        loadChildren: () => import('./bpbqe/bpbqe.module').then(m => m.EtatregBpbqeModule),
      },
      {
        path: 'bpitrs',
        loadChildren: () => import('./bpitrs/bpitrs.module').then(m => m.EtatregBpitrsModule),
      },
      {
        path: 'bpnaema',
        loadChildren: () => import('./bpnaema/bpnaema.module').then(m => m.EtatregBpnaemaModule),
      },
      {
        path: 'bppaysiso',
        loadChildren: () => import('./bppaysiso/bppaysiso.module').then(m => m.EtatregBppaysisoModule),
      },
      {
        path: 'bpsectinst',
        loadChildren: () => import('./bpsectinst/bpsectinst.module').then(m => m.EtatregBpsectinstModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EtatregEntityModule {}
